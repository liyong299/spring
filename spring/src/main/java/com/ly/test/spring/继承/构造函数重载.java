/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.�̳�
 * �ļ����ƣ����캯������.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��10��23�� ����11:55:35
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.�̳�;

/**
 * @����������
 * @�ļ����ƣ����캯������.java
 * @author ly
 */
public class ���캯������ {

	public ���캯������(String ab, Object cd) {
		System.out.println(ab + "-------2------" + cd);
	}

	public ���캯������(String ab, String cd) {
		System.out.println(ab + "-------1------" + cd);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new CB("BB", "AA");
	}
}

class PA {
	public PA() {};

	public PA(String p1, Object p2) {
		System.out.println(p1 + "-------4------" + p2);
	}
}

class CB extends PA {
	public CB(String p1, String p2) {
		System.out.println(p1 + "-------3------" + p2);
	}

}