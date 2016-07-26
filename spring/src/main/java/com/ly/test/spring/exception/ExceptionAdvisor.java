package com.ly.test.spring.exception;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionAdvisor implements ThrowsAdvice {

	public void recordLog() {
	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		// �ں�̨����������쳣�쳣��Ϣ��ͨ��log4j�����
		Logger log = Logger.getLogger(target.getClass().toString());

		log.info("**************************************************************");
		log.info("Error happened in class: " + target.getClass().getName());
		log.info("Error happened in method: " + method.getName());
		for (int i = 0; i < args.length; i++) {
			log.info("arg[" + i + "]: " + args[i]);
		}
		log.info("Exception class: " + ex.getClass().getName());
		log.info("ex.getMessage():" + ex.getMessage());
		ex.printStackTrace();
		log.info("**************************************************************");

		// �������ж��쳣�����ݲ�ͬ���쳣���ش���
		if (ex.getClass().equals(DataAccessException.class)) {
			ex.printStackTrace();
			throw new Exception("���ݿ����ʧ�ܣ�");
		} else if (ex.getClass().toString().equals(NullPointerException.class.toString())) {
			ex.printStackTrace();
			throw new Exception("������δ����ʼ���Ķ�������ǲ����ڵĶ���");
		} else if (ex.getClass().equals(IOException.class)) {
			ex.printStackTrace();
			throw new Exception("IO�쳣��");
		} else if (ex.getClass().equals(ClassNotFoundException.class)) {
			ex.printStackTrace();
			throw new Exception("ָ�����಻���ڣ�");
		} else if (ex.getClass().equals(ArithmeticException.class)) {
			ex.printStackTrace();
			throw new Exception("��ѧ�����쳣��");
		} else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
			ex.printStackTrace();
			throw new Exception("�����±�Խ��!");
		} else if (ex.getClass().equals(IllegalArgumentException.class)) {
			ex.printStackTrace();
			throw new Exception("�����Ĳ�������");
		} else if (ex.getClass().equals(ClassCastException.class)) {
			ex.printStackTrace();
			throw new Exception("����ǿ��ת������");
		} else if (ex.getClass().equals(SecurityException.class)) {
			ex.printStackTrace();
			throw new Exception("Υ����ȫԭ���쳣��");
		} else if (ex.getClass().equals(SQLException.class)) {
			ex.printStackTrace();
			throw new Exception("�������ݿ��쳣��");
		} else if (ex.getClass().equals(NoSuchMethodError.class)) {
			ex.printStackTrace();
			throw new Exception("����ĩ�ҵ��쳣��");
		} else if (ex.getClass().equals(InternalError.class)) {
			ex.printStackTrace();
			throw new Exception("Java������������ڲ�����");
		} else {
			ex.printStackTrace();
			throw new Exception("�����ڲ����󣬲���ʧ�ܣ�" + ex.getMessage());
		}
	}
}