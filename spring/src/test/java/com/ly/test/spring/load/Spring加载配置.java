/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.load
 * �ļ����ƣ�Spring��������.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��19�� ����3:18:21
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.load;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author ly
 * 
 */
public class Spring��������
{

	public Spring��������()
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * servlet ��ʹ�ã�����ᱨ��
	 */
	@Test
	public void testXmlWebApplicationContext()  
	{
		XmlWebApplicationContext ac = null;
		try
		{
			ac = new XmlWebApplicationContext();
			ac.setConfigLocations(new String[]{"app-context.xml"});
			ac.refresh();
			
			SayHello sayHello = (SayHello) ac.getBean("sayHello");
			System.out.println(sayHello.toString());
		}
		catch (Exception ex)
		{
			fail("error : " + ex.getMessage());
		}
		finally
		{
			if (ac != null)
			{
				//				ac.
			}
		}

		Assert.assertFalse(false);
	}

	@Test
	public void testClassPathResource()
	{
		Resource cr = null;
		try
		{
			cr = new ClassPathResource("app-context.xml");
			BeanFactory bf = new XmlBeanFactory(cr); // �Ѿ�����
			SayHello sayHello = (SayHello) bf.getBean("sayHello");
			System.out.println(sayHello.toString());
		}
		catch (Exception ex)
		{
			fail("error : " + ex.getMessage());
		}
		finally
		{
		}

		Assert.assertFalse(false);
	}
	
	@Test
	public void testFileSystemXmlApplicationContext()
	{
		ApplicationContext ac = null;
		try
		{
			ac = new FileSystemXmlApplicationContext("D:/Work/Workspace/scec_dis/spring/src/main/resources/app-context.xml");
			SayHello sayHello = (SayHello) ac.getBean("sayHello");
			System.out.println(sayHello.toString());
			
			String[] path = {"D:/Work/Workspace/scec_dis/spring/src/main/resources/app-context.xml"};
			ac = new FileSystemXmlApplicationContext(path);
			SayHello sayHello2 = (SayHello) ac.getBean("sayHello");
			System.out.println(sayHello2.toString());
		}
		catch (Exception ex)
		{
			fail("error : " + ex.getMessage());
		}
		finally
		{
		}

		Assert.assertFalse(false);
	}

	@Test
	public void testClassPathXmlApplicationContext()
	{
		ApplicationContext ac = null;
		try
		{
			ac = new ClassPathXmlApplicationContext("app-context.xml");
			SayHello sayHello = (SayHello) ac.getBean("sayHello");
			System.out.println(sayHello.toString());
		}
		catch (Exception ex)
		{
			fail("error : " + ex.getMessage());
		}
		finally
		{
		}

		Assert.assertFalse(false);
	}

}
