/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.load
 * �ļ����ƣ�SayHello.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��19�� ����3:21:48
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.load;

/**
 * @author ly
 *
 */
public class ˽�з����ĵ���
{
	public static void main(String[] args) throws ClassNotFoundException, Exception
	{
		Class cls = Class.forName("com.ly.test.spring.load.˽�з����ĵ���");
		Object obj = cls.newInstance();
		cls.getDeclaredMethod("runPrivate",null).invoke(obj, null);
	}
	
	private void runPrivate()
	{
		System.out.println("����һ��˽�з���");
	}
}
