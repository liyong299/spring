/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.java����
 * �ļ����ƣ�ServiceFactory.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����11:34:01
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.aop.java��̬����2;

/**
 * @author ly
 * 
 */
public class ServiceFactory
{
	public static void before()
	{
		System.out.println("ǰ���ռǣ���ӡ�����������..");
	}

	public static void after()
	{
		System.out.println("�����ռǣ���ӡ���ر������..");
	}

	public static void other()
	{
		System.out.println("����������..");
	}
}
