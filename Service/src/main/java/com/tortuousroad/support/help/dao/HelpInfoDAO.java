package com.tortuousroad.support.help.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tortuousroad.framework.common.persistence.BaseMybatisDAO;
import com.tortuousroad.support.help.entity.HelpInfo;

/**
 * 帮助信息
 */
@Repository
public class HelpInfoDAO extends BaseMybatisDAO {

	private static final String MAPPER_NAMESPACE = "com.tortuousroad.support.help.entity.HelpInfoMapper";
	
	/**
	 * 查询用于在页面显示的帮助信息
	 * @return
	 */
	public List<HelpInfo> getListForShow() {
		return findAll(MAPPER_NAMESPACE + ".selectListForShow");
	}
}