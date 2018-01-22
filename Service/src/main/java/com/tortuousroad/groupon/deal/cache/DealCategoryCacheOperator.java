package com.tortuousroad.groupon.deal.cache;

import com.tortuousroad.groupon.deal.entity.DealCategory;
import com.tortuousroad.framework.cache.CacheOperator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DealCategoryCacheOperator
 */
@Component
public class DealCategoryCacheOperator extends CacheOperator {

    /**
     * 向缓存中增加DealCategory
     * @param dealCategory
     */
    public void putDealCategory(DealCategory dealCategory) {
        super.putEntity("DealCategory." + dealCategory.getId(), dealCategory);

        // 维护其父类别的子类别缓存
        List<DealCategory> subCategories = getSubCategories(dealCategory.getParentId());
        if (subCategories != null && subCategories.size() > 0) {
            if (subCategories.contains(dealCategory)) {
                int index = 0;
                for (int i = 0; i < subCategories.size(); i++) {
                    if (subCategories.get(i).getId() == dealCategory.getId()) {
                        index = i;
                    }
                }
                subCategories.remove(index);
            }
            subCategories.add(dealCategory);

            putSubCategories(dealCategory.getParentId(), subCategories);
        }
    }

    /**
     * 根据ID从缓存中取出ProductCategory
     * @param categoryId
     * @return
     */
    public DealCategory getDealCategory(Long categoryId) {
        return super.getEntity("DealCategory." + categoryId.toString(), DealCategory.class);
    }

    /**
     * 根据ID从缓存中删除ProductCategory
     * @param categoryId
     */
    public void deleteDealCategory(Long categoryId) {
        // 维护其父类别的子类别缓存
        DealCategory dealCategory = getDealCategory(categoryId);
        if (dealCategory != null) {
            List<DealCategory> subCategories = getSubCategories(dealCategory.getParentId());
            if (subCategories != null && subCategories.size() > 0) {
                if (subCategories.contains(dealCategory)) {
                    subCategories.remove(dealCategory);
                }

                putSubCategories(dealCategory.getParentId(), subCategories);
            }
        }
        super.delete("DealCategory." + categoryId.toString());
    }

    /**
     * 获取全部DealCategory
     * @return
     */
    public List<DealCategory> getAllDealCategories() {
        return super.getEntitiesByKeyPrefix("DealCategory", DealCategory.class);
    }

    /**
     * 增加下级分类
     * @param parentId
     * @param dealCategories
     */
    public void putSubCategories(Long parentId, List<DealCategory> dealCategories) {
        super.putEntities("SubDealCategories." + parentId, dealCategories);
    }

    /**
     * 获取下级分类
     * @param parentId
     * @return
     */
    public List<DealCategory> getSubCategories(Long parentId) {
        return super.getEntities("SubDealCategories." + parentId, DealCategory.class);
    }

    /**
     * 删除下级分类
     * @param parentId
     */
    public void deleteSubCategories(Long parentId) {
        super.delete("SubDealCategories." + parentId.toString());
    }

}