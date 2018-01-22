package com.tortuousroad.admin.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tortuousroad.admin.security.entity.AdminFunction;
import com.tortuousroad.framework.common.page.PagingResult;
import com.tortuousroad.framework.common.persistence.BaseMybatisDAO;
import com.tortuousroad.framework.common.search.Search;

/**
 * AdminFunctionDAO
 */
@Repository
public class AdminFunctionDAO extends BaseMybatisDAO {
	
	private final String MAPPER_NAMESPACE = "com.tortuousroad.admin.security.entity.AdminFunctionMapper";

	/**
	 * 保存菜单信息
	 * @param adminFunction
	 * @return
     */
	public AdminFunction save(AdminFunction adminFunction) {
		super.save(MAPPER_NAMESPACE + ".insertSelective", adminFunction);
		return adminFunction;
	}

	/**
	 * 修改菜单信息
	 * @param adminFunction
     */
	public void update(AdminFunction adminFunction) {
		super.update(MAPPER_NAMESPACE + ".updateByPrimaryKeySelective", adminFunction);
	}

	/**
	 * 根据ID删除菜单
	 * @param id
     */
	public void deleteById(Long id) {
		super.delete(MAPPER_NAMESPACE + ".deleteByPrimaryKey", id);
	}

	/**
	 * 分页查询
	 * @param search
	 * @return
     */
	public PagingResult<AdminFunction> findPage(Search search) {
		return super.findForPage(MAPPER_NAMESPACE + ".countPageFunctions",
				MAPPER_NAMESPACE + ".selectPageFunctions", search);
	}

	/**
	 *
	 * @return
     */
	public List<AdminFunction> findAll() {
		return super.findAll(MAPPER_NAMESPACE + ".selectAll");
	}
	
}
