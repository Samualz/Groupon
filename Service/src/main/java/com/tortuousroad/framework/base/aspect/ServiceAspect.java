package com.tortuousroad.framework.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Service中方法调用统计Service中方法调用统计
 */
//@Component
//@Aspect
public class ServiceAspect {
	
	//包、类、方法、参数、耗时
	//切面收集日志，放入内存（CurrentHashMap），单独线程定时从内存中把日志转移到文件或数据库（*.log或者mongoDB）
	//固定代码 or SPI扩展
	/**
	 * 方法调用切面
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "execution(public * com.tortuousroad..service.*Service.*(..))")
	public Object aroundServiceMethod(final ProceedingJoinPoint pjp) throws Throwable {
		Object returnVal = null;
		long start = System.currentTimeMillis();
		try {
			for (Object obj : pjp.getArgs()) {
				System.out.println(obj);
			}
			returnVal = pjp.proceed();
			System.out.println("S " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()
					+ " ：" + (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("F " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()
					+ " ：" + (System.currentTimeMillis() - start) + "ms");
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnVal;
	}
	
//	@Before(value = "execution(public * com.tortuousroad..service.*Service.*(..))")
//	public Object beforeServiceMethod(final JoinPoint jp) throws Throwable {
//		System.out.println("before");
//		return "";
//	}
//	
//	@After(value = "execution(public * com.tortuousroad..service.*Service.*(..))")
//	public Object afterServiceMethod() {
//		System.out.println("after");
//		return "";
//	}

}
