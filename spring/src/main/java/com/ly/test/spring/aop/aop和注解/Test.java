/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.aop和注解
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月29日 下午1:58:18
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.aop.aop和注解;

import com.ly.test.spring.注解.UserAction;

/**
 * @author ly
 *
 */
public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String aopFile = "D:\\Work\\Workspace\\scec_dis\\spring\\src\\main\\resources\\aop_config.properties";
		String annoFile = "D:\\Work\\Workspace\\scec_dis\\spring\\src\\main\\resources\\anno.properties";
		SpringTest1 springContext = new SpringTest1(annoFile, aopFile);
		
		UserAction userAction = (UserAction) springContext.getBean("userAction");
		
		System.out.println("===========================");
		userAction.life();
	}

}
