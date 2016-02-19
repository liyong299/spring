package spring.dao1;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * ͨ��������������������Դע�뵽��ͬ���̵߳�������
 * @author ly
 *
 */
@Component
public class DataSourceInterceptor
{
	public void setdataSourceOne(JoinPoint jp)
	{
		DatabaseContextHolder.setCustomerType("dataSourceOne");
	}

	public void setdataSourceTwo(JoinPoint jp)
	{
		DatabaseContextHolder.setCustomerType("dataSourceTwo");
	}
}