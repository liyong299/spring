/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.aop和注解
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月29日 下午1:58:18
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
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
