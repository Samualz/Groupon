package com.tortuousroad.support.address.entity;

import com.tortuousroad.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 地址
 */
public class Address extends BaseEntity {

    @Getter @Setter private Long userId;//地址归属用户ID
    @Getter @Setter private String receiver;//收货人
    @Getter @Setter private String area;//地区
    @Getter @Setter private String detail;//详细地址
    @Getter @Setter private AddressType type;//类型:家、公司
    @Getter @Setter private String phone;//联系电话
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date updateTime;

}
