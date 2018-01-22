package com.tortuousroad.groupon.cart.entity;

import com.tortuousroad.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 购物车
 */
public class Cart extends BaseEntity {

    @Getter @Setter private Long userId;

    @Getter @Setter private Long dealId;

    @Getter @Setter private Long dealSkuId;

    @Getter @Setter private Integer count;

    @Getter @Setter private Date createTime;

    @Getter @Setter private Date updateTime;

}
