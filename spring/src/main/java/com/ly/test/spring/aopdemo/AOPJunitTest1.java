/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao1
 * 文件名称：DaoTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月15日 上午11:20:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.aopdemo;

/**
 * com.ly.test.spring.aopdemo
 *
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author ly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")  
public class AOPJunitTest1
{
    @Autowired  
    private ApplicationContext applicationContext;  
    
    @Test  
    public void testList() {
	  CakeService jack = (CakeService) applicationContext.getBean(CakeService.class);  
	  jack.test("abcd");
	  
	  SwimService swimService = (SwimService) applicationContext.getBean(SwimService.class);  
//	  swimService.游泳();
	  swimService.上岸休息();
    }  
} 
