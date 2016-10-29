package com.ly.test.spring.task;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduleServer {

	@PostConstruct
	public void firstRun() {
		System.out.println("firstRun");
	}

	@Scheduled(cron = "0/1 * * * * ?")
	public void test() {
		System.out.println(System.currentTimeMillis() + " || schedule");
	}

	@PreDestroy
	public void dostory() {
		System.out.println("I'm  destory method  using  @PreDestroy.....");
	}
}
