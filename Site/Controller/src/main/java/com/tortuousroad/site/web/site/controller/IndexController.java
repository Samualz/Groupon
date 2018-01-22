package com.tortuousroad.site.web.site.controller;

import com.tortuousroad.framework.common.page.PagingResult;
import com.tortuousroad.groupon.deal.entity.Deal;
import com.tortuousroad.groupon.deal.entity.DealCategory;
import com.tortuousroad.groupon.deal.service.DealCategoryService;
import com.tortuousroad.groupon.deal.service.DealService;
import com.tortuousroad.site.web.site.dto.IndexCategoryDealDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController extends BaseSiteController {

	@Autowired private DealService dealService;
	@Autowired private DealCategoryService categoryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		//1.分类
		//2.首页商品
		//3.每个大分类下显示8个商品

		List<DealCategory> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);

		//根据IP确定
		Long areaId = getAreaId(request);


		//1.构造一个结构体存放每个分类的8个商品
		//2.页面循环结构体的集合
		//3.结构体包含8个商品,1各分类
		//4.8个商品分两组

		List<IndexCategoryDealDTO> indexCategoryDealDTOs = new ArrayList<>();
		categories.forEach(category -> {
			List<Deal> deals = dealService.getDealsForIndex(areaId, category.getSelfAndChildrenIds());
			indexCategoryDealDTOs.add(new IndexCategoryDealDTO(category, deals));
		});

		model.addAttribute("indexCategoryDealDTOs", indexCategoryDealDTOs);
		return "index";
	}

	/**
	 * 商品搜索
	 * @param s
	 * @param model
	 * @param page
     * @return
     */
	@RequestMapping(value = "/search")
	public String search(String s, Model model, Integer page) {
		List<DealCategory> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
//		model.addAttribute("categoryList", categories);
		try {
			//get提交中文为ISO-8859-1,需转成UTF-8
			String word = new String(s.getBytes("ISO-8859-1"), "UTF-8");
			PagingResult<Deal> pageResult = dealService.searchByName(word, page, 3);
			model.addAttribute("pagingDealList", pageResult);
			model.addAttribute("s", word);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "/search";
	}

}