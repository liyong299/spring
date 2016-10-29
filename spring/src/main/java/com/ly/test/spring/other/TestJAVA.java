/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.other
 * �ļ����ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��1�� ����1:42:26
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.other;

import org.junit.Test;


/**
 * @author ly
 *
 */

public class TestJAVA
{
	@Test
	public void Testִ��˳��() {
		try{
			System.out.println("����0");
			throw new Exception("����1");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("����2");
			throw new RuntimeException("����22");
		} finally {
			System.out.println("����3");
		}
	}

	public static void main(String[] ar) throws Exception {
		try {
			System.out.println("����0");
			throw new Exception("����1");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("����2");
			//			throwException();
			throw new Exception(ex);
		} finally {
			System.out.println("����3");
		}
	}

	public static void throwException() {
		throw new RuntimeException("����22");
	}
}
