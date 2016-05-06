/**
 * ��Ŀ���ƣ�spring.dao
 * �ļ�������spring.dao1
 * �ļ����ƣ�DaoTest.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��15�� ����11:20:57
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
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
//	  swimService.��Ӿ();
	  swimService.�ϰ���Ϣ();
    }  
} 
