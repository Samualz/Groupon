package com.tortuousroad.framework.base.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * SpringApplicationContext
 */
public class SpringApplicationContext implements ApplicationContextAware {
	
	private static ApplicationContext context;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringApplicationContext.context = context;
//		for (String s : context.getBeanDefinitionNames()) {
//			System.out.println(s);
//		}
	}
	
	/**
	 * getBean
	 * @param beanId	bean id
	 * @return	bean实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return (T) context.getBean(beanId);
	}
	
	/**
	 * getBean
	 * @param clazz	bean类型
	 * @return	bean实例
	 */
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		return context.getBeansOfType(type);
	}

}
