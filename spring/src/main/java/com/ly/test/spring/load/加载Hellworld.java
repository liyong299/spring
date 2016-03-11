/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.load
 * �ļ����ƣ�����Hellworld.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��19�� ����2:21:11
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.load;

import java.io.File;
import java.lang.reflect.Method;

import org.aspectj.util.FileUtil;

/**
 * @author ly
 *
 */
public class ����Hellworld
{

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, Exception
	{
		ClassLoader classLoader = ����Hellworld.class.getClassLoader();
		System.out.println(classLoader);
		System.out.println(classLoader.getParent());
		System.out.println(classLoader.getParent().getParent());
		
		// ��Ҫ���ص��ļ�·�������ļ����ڵ�ǰclassesĿ¼�£��ø÷�����
		String clsPath = "D:/Work/Workspace/other/dup/target/classes/HelloWorld.class";
		Class cls = new FileClassLoader().load("HelloWorld", clsPath);
		
		Class cls1 = new FileClassLoader().load("HelloWorld", new File(clsPath));  // �������鲻�ᱨ��
		
		Object object = cls.newInstance();
		Method method = cls.getMethod("testLoader", null);
		method.invoke(object, null);
		
		// �Զ����������� û�и���findClass����
		ClassLoader myClassLoader = cls.getClassLoader();
		
		// ֱ�ӽ�class�ļ�������classesĿ¼�£��������·������ء�
		Class cls2 = Class.forName("HelloWorld", true, classLoader);
		Object object2 = cls2.newInstance(); 	
		Method method2 = cls2.getMethod("testLoader", null);
		method2.invoke(object2, null);
		
		// ֱ�ӽ�class�ļ�������classesĿ¼�£��������·������ء�
		Class cls3 = classLoader.loadClass("HelloWorld");
		Object obj3 = cls3.newInstance();
		cls3.getMethod("testLoader", null).invoke(obj3, null);
		
		// �������в��ܷ���
		Class cls4 = Class.forName("com.ly.test.spring.load.˽�з����ĵ���");
		Object obj4 = cls4.newInstance();
		cls4.getDeclaredMethod("runPrivate",null).invoke(obj4, null);
	}
}
