/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.aop.aop��ע��
 * �ļ����ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��29�� ����1:58:18
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ly
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class Junit4Test {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testList() {

		ExcetptionHandlerTest aa = (ExcetptionHandlerTest) applicationContext.getBean("excetptionHandlerTest");

		System.out.println("===========================");
		aa.testException();
	}

}
