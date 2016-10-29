package com.ly.test.spring.cacheable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Cacheable(value = "common", key = "'id_'+#id")
	public User selectByPrimaryKey(String id) {
		User user = new User();
		user.setId(id);
		user.setCode("123");
		user.setName("List");

		Order order = new Order();
		order.setCode("345");
		order.setId(UUID.randomUUID().toString());
		order.setPrice(1.2d);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);

		user.setOrders(orders);
		System.out.println("query success!");
		return user;
	}

	@CacheEvict(value = "common", key = "'id_'+#id")
	public void deleteByPrimaryKey(String id) {
		System.out.println("delete success!");
	}
}