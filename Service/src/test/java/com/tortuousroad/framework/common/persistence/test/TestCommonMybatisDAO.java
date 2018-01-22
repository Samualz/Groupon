package com.tortuousroad.framework.common.persistence.test;

import com.tortuousroad.framework.common.persistence.CommonMybatisDAO;
import org.junit.Test;

/**
 * 单元测试类
 */
public class TestCommonMybatisDAO {

    @Test
    public void testSave() {
        CommonMybatisDAO.save(null);
    }

}
