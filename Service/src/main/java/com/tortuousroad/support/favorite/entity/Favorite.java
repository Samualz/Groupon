package com.tortuousroad.support.favorite.entity;

import com.tortuousroad.framework.base.entity.BaseEntity;
import com.tortuousroad.support.area.entity.AreaType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 收藏
 */
public class Favorite extends BaseEntity {

    @Getter @Setter private Long userId;
    @Getter @Setter private Long dealId;
    @Getter @Setter private Long dealSkuId;
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date updateTime;

}
