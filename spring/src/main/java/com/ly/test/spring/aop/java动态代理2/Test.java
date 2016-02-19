package com.ly.test.spring.aop.java动态代理2;

import com.ly.test.spring.aop.java动态代理.UserService;
import com.ly.test.spring.aop.java动态代理.UserServiceImpl;

public class Test
{

	public static void main(String[] args)
	{
		UserService userService = (UserService) new MyProxy().bind(new UserServiceImpl());
		userService.eat();
		userService.sleep();
	}
}
