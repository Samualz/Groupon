package com.tortuousroad.groupon.deal.entity;

import com.tortuousroad.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Deal即一个单品
 */
public class Deal extends BaseEntity {

    @Getter @Setter private Long areaId; // 地区ID

    @Getter @Setter private String areaName; // 地区

    @Getter @Setter private Long skuId; // skuID

    @Getter @Setter private Integer dealClass; // 是否虚拟商品

    @Getter @Setter private Long merchantId; // 商家ID

    @Getter @Setter private Integer merchantSku; // 商家SKU 编号

    @Getter @Setter private String dealTitle; // 商品标题

    @Getter @Setter private Integer dealPrice; // 商品价格

    @Getter @Setter private Integer merchantPrice; // 进货价

    @Getter @Setter private Integer marketPrice; // 市场价

    @Getter @Setter private Integer settlementPriceMax; // 最大可接受结算价格

    @Getter @Setter private Integer settlementPrice; // 结算价

    @Getter @Setter private Integer discount; // 折扣

    @Getter @Setter private Integer bonusPoints; // 积分

    @Getter @Setter private Integer dealType; // 商品类型

    @Getter @Setter private Long imageId; // 对应商品图片

    @Getter @Setter private String imageGenPath; // 对应商品图片路径

    @Getter @Setter private Integer dealLevel; // 商品优先级

    @Getter @Setter private Integer maxPurchaseCount; // 最大购买数量

    @Getter @Setter private Integer publishStatus; // 发布状态

    @Getter @Setter private Integer inventoryAmount; // 商品库存数量

    @Getter @Setter private Integer vendibilityAmount; // 商品可售数量

    @Getter @Setter private Integer oosStatus; // 是否售罄标识

    @Getter @Setter private Date startTime; // 商品销售开始时间

    @Getter @Setter private Date endTime; // 商品销售结束时间

    @Getter @Setter private Date publishTime; // 商品上架时间

    @Setter @Getter private String merchantCode; // 商品唯一编码

    @Getter @Setter private Date createTime; // 商品创建时间

    @Getter @Setter private Date updateTime; // 商品更新时间

    @Getter @Setter private DealDetail dealDetail; // 商品对应描述

    @Getter @Setter private Boolean downShelf; // 是否下架的标识

    @Getter @Setter private Integer categoryId; // 商品类别ID

    public boolean isStart() {//是否开始团购
        return new Date().after(this.getStartTime());
    }

    public boolean isEnd() {//是否结束
        return new Date().after(this.getEndTime());
    }

}