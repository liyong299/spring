/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.java��̬����
 * �ļ����ƣ�UserServiceImpl.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����11:19:55
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.aop.java��̬����;

/**
 * @author ly
 *
 */
public class UserServiceImpl implements UserService
{

	/* (non-Javadoc)
	 * @see com.ly.test.spring.aop.java��̬����.UserService#eat()
	 */
	public void eat()
	{
		System.out.println(Thread.currentThread().getName() + " | ���ڳԷ���������");
	}

	/* (non-Javadoc)
	 * @see com.ly.test.spring.aop.java��̬����.UserService#sleep()
	 */
	public void sleep()
	{
		System.out.println(Thread.currentThread().getName() + " | ����˯����������");
	}

}
