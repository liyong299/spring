package com.ly.test.spring.aop.java��̬����2;

import com.ly.test.spring.aop.java��̬����.UserService;
import com.ly.test.spring.aop.java��̬����.UserServiceImpl;

public class Test
{

	public static void main(String[] args)
	{
		UserService userService = (UserService) new MyProxy().bind(new UserServiceImpl());
		userService.eat();
		userService.sleep();
	}
}
