package com.ly.test.spring.exthrow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @������������Ҫ��ӡ�쳣��Ϣ�����������ء�
 * @�ļ����ƣ�Test.java
 * @author ly
 */
public class Test {

	static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			System.out.println("�����쳣��");
		}
	}

	public static void test1() {
		try {
			test2();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}

	public static void test2() {
		throw new RuntimeException("ceshi");
	}
}
