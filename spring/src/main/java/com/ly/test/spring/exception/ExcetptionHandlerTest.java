/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.exception
 * �ļ����ƣ������쳣����.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��7��25�� ����4:19:19
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.exception;

import org.springframework.stereotype.Service;

/**
 * @����������
 * @�ļ����ƣ������쳣����.java
 * @author ly
 */
@Service
public class ExcetptionHandlerTest {

	/**
	 * �쳣���ش��� ֻ������web�У�Ӧ���Ǻ�servlet��ĳЩע�����
	 * 
	 * @param request
	 *            HTTP����
	 * @param ex
	 *            �쳣
	 * @return �����쳣��ͼ��
	 */
	/*
	 * @ExceptionHandler(value = { BindException.class, Exception.class })
	 * protected void checkedException(String tenantKey, String bodyJson,
	 * Exception ex) { System.out.println("+++++++++++++++++++++++++");
	 * System.out.println("�����쳣����.checkedException"); }
	 */

	public void testException() {
		System.out.println("�����쳣����.testException");
		throw new RuntimeException("---------");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
