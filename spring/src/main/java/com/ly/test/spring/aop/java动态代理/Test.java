/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.java��̬����
 * �ļ����ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����11:28:14
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.aop.java��̬����;

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
