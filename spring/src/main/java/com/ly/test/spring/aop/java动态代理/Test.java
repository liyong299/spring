/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.java动态代理
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 上午11:28:14
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.aop.java动态代理;

import java.lang.reflect.Proxy;

/**
 * @author ly
 *
 */
public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		UserService userService = new UserServiceImpl();
		UserService proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), 
				userService.getClass().getInterfaces(), 
				new UserServiceInvocation(userService));
		System.out.println("====================================");
		try
		{
//			Thread.currentThread().sleep(3000000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		proxy.eat();
		proxy.sleep();
	}

}
