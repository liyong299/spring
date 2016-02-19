/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.java动态代理
 * 文件名称：UserServiceImpl.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 上午11:19:55
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.aop.java动态代理;

/**
 * @author ly
 *
 */
public class UserServiceImpl implements UserService
{

	/* (non-Javadoc)
	 * @see com.ly.test.spring.aop.java动态代理.UserService#eat()
	 */
	public void eat()
	{
		System.out.println(Thread.currentThread().getName() + " | 正在吃饭。。。。");
	}

	/* (non-Javadoc)
	 * @see com.ly.test.spring.aop.java动态代理.UserService#sleep()
	 */
	public void sleep()
	{
		System.out.println(Thread.currentThread().getName() + " | 正在睡觉。。。。");
	}

}
