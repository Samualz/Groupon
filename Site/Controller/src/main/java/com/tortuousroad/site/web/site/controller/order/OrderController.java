package com.tortuousroad.site.web.site.controller.order;

import com.tortuousroad.groupon.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tortuousroad.framework.common.search.Search;
import com.tortuousroad.site.web.site.controller.BaseSiteController;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseSiteController {
	
	@Autowired
	private OrderService orderService;
	
//	@RequestMapping(value = "/list")
//	public String listOrder(int userId, Model model) {
//		model.addAttribute("rows", orderService.getOrderByUserId(userId));
//		return "/order/order_list";
//	}
//
//	@RequestMapping(value = "/detail")
//	public String viewDetail(Model model, Long orderId, Search search) {
//		model.addAttribute("order", this.orderService.getOrderAndDetailByOrderId(orderId));
//		return "/order/order_detail";
//	}
}