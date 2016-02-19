/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.aop模拟
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 下午2:14:27
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.aop.aop模拟;

import com.ly.test.spring.aop.java动态代理.UserService;

/**
 * @author ly
 *
 */
public class Test
{
	private UserService userService;
	
	public void test()
	{
		userService.eat();
		userService.sleep();
	}
	
	public void print()
	{
		System.out.println("---------------");
	}
	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		// 
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class clsData = classLoader.loadClass("com.ly.test.spring.aop.aop模拟.Test");
		Test test = (Test) clsData.newInstance();
		test.print();
	}

}
