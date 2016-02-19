/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.ע��
 * �ļ����ƣ�UserAction.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����2:50:53
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.ע��;

import com.ly.test.spring.aop.java��̬����.UserService;

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
