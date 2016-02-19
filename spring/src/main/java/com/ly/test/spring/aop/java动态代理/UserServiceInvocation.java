/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.java动态代理
 * 文件名称：UserServiceInvocation.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 上午11:21:35
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.aop.java动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 用户服务的代理类
 * @author ly
 *
 */
public class UserServiceInvocation implements InvocationHandler
{
	private Object obj;
	private long startTime;
	
	public UserServiceInvocation(Object obj)
	{
		this.obj = obj;
	}
	
	public void before()
	{
		this.startTime = System.nanoTime();
		System.out.println("----------------before-------------------");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		Object result = null;
		try
		{
			before();
			System.out.printf("当前代理类 %20s ", proxy.getClass().getSimpleName());
			System.out.println();
			result = method.invoke(obj, args);
			after();
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	
	public void after()
	{
		System.out.println("运行时间：" + (System.nanoTime() - startTime) / 1000000);
		System.out.println("----------------after-------------------");
	}
}
