package com.tortuousroad.admin.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuousroad.admin.security.dao.AdminUserDAO;
import com.tortuousroad.admin.security.dao.AdminUserRoleDAO;
import com.tortuousroad.admin.security.entity.AdminUser;
import com.tortuousroad.framework.common.page.PagingResult;
import com.tortuousroad.framework.common.search.Search;

/**
 * AdminUserService
 */
@Service
public class AdminUserService {
	
	@Autowired private AdminUserDAO adminUserDAO;
	
	@Autowired private AdminUserRoleDAO adminUserRoleDAO;

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
     */
	public AdminUser getUserByUsername(String username) {
		AdminUser adminUser = adminUserDAO.getUserByUsername(username);
		adminUser.setAdminUserRoles(this.adminUserRoleDAO.findByUserId(adminUser.getId()));
		return adminUser;
	}

	/**
	 * 分页查询
	 * @param search
	 * @return
     */
	public PagingResult<AdminUser> findAdminUserForPage(Search search) {
		PagingResult<AdminUser> result = this.adminUserDAO.findPage(search);
		for (AdminUser adminUser : result.getRows()) {
			adminUser.setAdminUserRoles(this.adminUserRoleDAO.findByUserId(adminUser.getId()));
		}
		return result;
	}

	/**
	 * 新建
	 * @param adminUser
     */
	public void addAdminUser(AdminUser adminUser) {
		this.adminUserDAO.save(adminUser);
	}

	/**
	 * 更新
	 * @param adminUser
     */
	public void updateAdminUser(AdminUser adminUser) {
		this.adminUserDAO.update(adminUser);
	}

	/**
	 * 删除
	 * @param adminUserId
     */
	public void deleteAdminUser(Long adminUserId) {
		this.adminUserDAO.deleteById(adminUserId);
	}
	
	public List<AdminUser> getAll() {
		return adminUserDAO.getAll();
	}
}