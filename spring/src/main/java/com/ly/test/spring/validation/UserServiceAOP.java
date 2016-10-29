package com.ly.test.spring.validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class UserServiceAOP {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.ly.test.spring.validation.UserService.*(..))")
	public void aroundJoin() {}

	@Around("aroundJoin()")
	public Object around(ProceedingJoinPoint point) {
		if (logger.isDebugEnabled())
			logger.debug("@before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());

		StopWatch watch = new StopWatch();
		watch.start();

		Object obj = null;
		try {

			Object[] arguments = point.getArgs();
			int length = arguments.length;
			int k = 0;
			while (k < length) {
				Object argument = arguments[k];
				//				SmartValidate.validate(argument);
				k++;
			}

			obj = point.proceed();

		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		if (watch.isRunning())
			watch.stop();

		if (logger.isDebugEnabled())
			logger.debug("@after：执行时间为： [" + watch.getTotalTimeMillis() + "]毫秒");
		return obj;
	}
}
