package spring.dao3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 通过拦截器方法，将数据源注入到不同的线程的上下文
 * @author ly
 *
 */
@Component
@Aspect
public class DataSourceInterceptor
{
	/**
	 * 功能描述：<p>将DataSource的key放入到线程上下文中</p>
	 * 实现逻辑：<p>从第一个参数中获取DataSource</p>
	 * @param jp
	 */
	@Before("execution (* spring.dao3.impl.DaoUtil.*(..))") //此处为pointcut
	public void setDataSource(JoinPoint jp)
	{		
		DatabaseContextHolder.setCustomerType(jp.getArgs()[0].toString());
	}
}