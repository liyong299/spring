/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.load
 * 文件名称：Spring加载配置.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月19日 下午3:18:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
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
public class Spring加载配置
{

	public Spring加载配置()
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
	 * servlet 中使用，这里会报错
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
			BeanFactory bf = new XmlBeanFactory(cr); // 已经过期
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
