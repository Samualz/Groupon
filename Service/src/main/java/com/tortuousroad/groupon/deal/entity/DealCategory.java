package com.tortuousroad.groupon.deal.entity;

import com.tortuousroad.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Deal分类
 */
public class DealCategory extends BaseEntity {

    @Getter @Setter private Long parentId; // 父类别ID

    @Getter @Setter private String name; // 类别名称

    @Getter @Setter private String urlName; // 类别Url名称

    @Getter @Setter private Integer publishStatus; // 发布状态

    @Getter @Setter private Date createTime; // 创建时间

    @Getter @Setter private Integer orderNum; // 排序序号

    @Getter @Setter private Integer deep; // 层级深度

    @Getter @Setter private List<DealCategory> children;

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof DealCategory)) {
            return false;
        }

        DealCategory other = (DealCategory) obj;
        if (other.getId() == null && getId() == null) {
            return true;
        }

        if (other.getId() != null && getId() != null) {
            return getId().equals(other.getId());
        }

        return false;
    }

    public List<Long> getSelfAndChildrenIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(this.getId());

        if (null != children && !children.isEmpty()) {
            ids.addAll(BaseEntity.idList(children));
            children.forEach(child -> getSub(child.getChildren(), ids));
        }
        return ids;
    }

    private void getSub(List<DealCategory> list, List<Long> ids) {
        if (null != list && !list.isEmpty()) {
            ids.addAll(BaseEntity.idList(list));
            list.forEach(child -> getSub(child.getChildren(), ids));
        }
    }

}
