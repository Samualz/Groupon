package com.tortuousroad.support.message.entity;

import com.tortuousroad.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 地区
 */
public class Message extends BaseEntity {

    @Getter @Setter private Long userId;
    @Getter @Setter private String title;//标题
    @Getter @Setter private String content;//内容
    @Getter @Setter private Integer readed;//0未读，1已读
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date updateTime;

}
