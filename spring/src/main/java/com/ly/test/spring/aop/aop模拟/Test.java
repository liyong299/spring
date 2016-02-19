/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.aopģ��
 * �ļ����ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����2:14:27
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.aop.aopģ��;

import com.ly.test.spring.aop.java��̬����.UserService;

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
		Class clsData = classLoader.loadClass("com.ly.test.spring.aop.aopģ��.Test");
		Test test = (Test) clsData.newInstance();
		test.print();
	}

}
