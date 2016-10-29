package com.ly.test.spring.cacheable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @功能详解：
 * @author ly
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context-cacheable.xml")
public class Junit4Test {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testAddChannel() {

		ChannelService aa = (ChannelService) applicationContext.getBean("channelService");

		Channel channel = aa.queryChannelInfo("abcd"); // 加入缓存
		org.springframework.util.Assert.notNull(channel.getSettings());

		channel = aa.queryChannelInfo("abcd"); // 从缓存中读取

		org.springframework.util.Assert.notNull(channel.getSettings());
		ChannelSettings settings = channel.getSettings();
		System.out.println(settings.getLogLength());

		aa.deleteByPrimaryKey("abcd"); // 从缓存读入数据之后，清除掉缓存中的值。
	}

	@Test
	public void testDelChannel() {

		ChannelService aa = (ChannelService) applicationContext.getBean("channelService");

		aa.deleteByPrimaryKey("abcd");

		org.springframework.util.Assert.isTrue(true);
	}

	@Test
	public void testAddUser() {

		UserService aa = (UserService) applicationContext.getBean("userService");

		User user = aa.selectByPrimaryKey("111111");

		org.springframework.util.Assert.notEmpty(user.getOrders());

		Order order = user.getOrders().get(0);

		System.out.println(order.getCode());

	}

	@Test
	public void testDelUser() {

		UserService aa = (UserService) applicationContext.getBean("userService");

		aa.deleteByPrimaryKey("111111");

		org.springframework.util.Assert.isTrue(true);
	}
}
