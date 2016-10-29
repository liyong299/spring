package com.ly.test.spring.validation;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void addUser(User user) {
		System.out.println(user);
	}

}
