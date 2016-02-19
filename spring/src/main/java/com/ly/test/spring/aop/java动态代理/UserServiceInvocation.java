/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.java��̬����
 * �ļ����ƣ�UserServiceInvocation.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����11:21:35
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.aop.java��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * �û�����Ĵ�����
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
			System.out.printf("��ǰ������ %20s ", proxy.getClass().getSimpleName());
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
		System.out.println("����ʱ�䣺" + (System.nanoTime() - startTime) / 1000000);
		System.out.println("----------------after-------------------");
	}
}
