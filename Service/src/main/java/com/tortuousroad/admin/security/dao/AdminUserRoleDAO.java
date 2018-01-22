package com.tortuousroad.admin.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tortuousroad.admin.security.entity.AdminUserRole;
import com.tortuousroad.framework.common.page.PagingResult;
import com.tortuousroad.framework.common.persistence.BaseMybatisDAO;
import com.tortuousroad.framework.common.search.Search;

/**
 * AdminUserRoleDAO
 */
@Repository
public class AdminUserRoleDAO extends BaseMybatisDAO {
	
	private final String MAPPER_NAMESPACE = "com.tortuousroad.admin.security.entity.AdminUserRoleMapper";

	/**
	 * 新建用户角色
	 * @param adminUserRole
     */
	public void save(AdminUserRole adminUserRole) {
		super.save(MAPPER_NAMESPACE + ".insertSelective", adminUserRole);
	}

	/**
	 * 批量新建用户角色
	 * @param adminUserRoles
     */
	public void saveBatch(List<AdminUserRole> adminUserRoles) {
		super.saveBatch(MAPPER_NAMESPACE + ".batchInsertAdminUserRoles", adminUserRoles);
	}

	/**
	 * 根据用户ID删除
	 * @param adminUserId
     */
	public void deleteByUserId(Long adminUserId) {
		super.delete(MAPPER_NAMESPACE + ".deleteByUserId", adminUserId);
	}

	/**
	 * 根据用户ID查询
	 * @param adminUserId
	 * @return
     */
	public List<AdminUserRole> findByUserId(Long adminUserId) {
		return super.findAll(MAPPER_NAMESPACE + ".findByUserId", adminUserId);
	}

}
