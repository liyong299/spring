/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.aop��ע��
 * �ļ����ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��29�� ����1:58:18
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.aop.aop��ע��;

import com.ly.test.spring.ע��.UserAction;

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
		String aopFile = "D:\\Work\\Workspace\\scec_dis\\spring\\src\\main\\resources\\aop_config.properties";
		String annoFile = "D:\\Work\\Workspace\\scec_dis\\spring\\src\\main\\resources\\anno.properties";
		SpringTest1 springContext = new SpringTest1(annoFile, aopFile);
		
		UserAction userAction = (UserAction) springContext.getBean("userAction");
		
		System.out.println("===========================");
		userAction.life();
	}

}
