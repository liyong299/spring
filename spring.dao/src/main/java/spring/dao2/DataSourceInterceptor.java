package spring.dao2;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * 通过拦截器方法，将数据源注入到不同的线程的上下文
 * @author ly
 *
 */
@Component
public class DataSourceInterceptor
{
	public void setdataSourceOne(JoinPoint jp)
	{
		System.out.println("-----------------------------");
		DatabaseContextHolder.setCustomerType("dataSourceOne");
	}

	public void setdataSourceTwo(JoinPoint jp)
	{
		System.out.println("----------------222222222-------------");
		DatabaseContextHolder.setCustomerType("dataSourceTwo");
	}
}