/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.注解
 * 文件名称：UserAction.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 下午2:50:53
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.注解;

import com.ly.test.spring.aop.java动态代理.UserService;

/**
 * @author ly
 *
 */
public class UserAction
{
	@MyAutowired
	private UserService userService;
	
	public void life()
	{
		userService.eat();
		userService.sleep();
	}
	
	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
