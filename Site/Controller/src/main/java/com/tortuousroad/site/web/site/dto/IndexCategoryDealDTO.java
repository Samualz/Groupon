package com.tortuousroad.site.web.site.dto;

import com.tortuousroad.groupon.deal.entity.Deal;
import com.tortuousroad.groupon.deal.entity.DealCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页显示商品
 */
public class IndexCategoryDealDTO {

    @Getter @Setter private DealCategory category;
    @Getter @Setter private List<Deal> first;
    @Getter @Setter private List<Deal> second;


    public IndexCategoryDealDTO(DealCategory category, List<Deal> deals) {
        this.category = category;

        //1.从deals截取出前4个赋值给first
        //2.再4个赋值给second
        //3.不够怎么办?不够4个与不够8个
        //4.多了怎么办?
        if (CollectionUtils.isEmpty(deals)) {
            this.first = new ArrayList<>();
            this.second = new ArrayList<>();
        } else if (deals.size() > 4) {
            this.first = deals.subList(0, 4);
            if (deals.size() > 8) {
                this.second = deals.subList(4, 8);
            } else {
                this.second = deals.subList(4, deals.size());
            }
        } else {
            this.first = new ArrayList<>(deals);
            this.second = new ArrayList<>();
        }
    }

}