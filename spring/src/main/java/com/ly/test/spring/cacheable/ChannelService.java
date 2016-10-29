package com.ly.test.spring.cacheable;

import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

	@Cacheable(value = "redis1", key = "'id_'+#id")
	public Channel queryChannelInfo(String id) {
		Channel channel = new Channel();
		channel.setId(id);
		channel.setCode("123");
		channel.setName("List");

		ChannelSettings setting = new ChannelSettings();
		setting.setPreAreaTime(30);
		setting.setId(UUID.randomUUID().toString());
		setting.setLogLength(0);

		channel.setSettings(setting);
		System.out.println("query success!");
		return channel;
	}

	@CacheEvict(value = "redis1", key = "'id_'+#id")
	public void deleteByPrimaryKey(String id) {
		System.out.println("delete success!");
	}
}