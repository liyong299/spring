/**
 * ��Ŀ���ƣ�spring.dao
 * �ļ�������spring.dao1
 * �ļ����ƣ�DaoTest.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��15�� ����11:20:57
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package spring.dao2;

/**
 * @author ly
 *
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import spring.dao2.one.BrandDaoImpl;
import spring.dao2.two.CityDaoImpl;


/**
 * 
 * @author ly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app_dao2.xml")  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
public class DaoTest
{
    @Resource  
    private BrandDaoImpl brandDao;  
  
    @Resource
    private CityDaoImpl cityDao;
  
    @Test  
    public void testList()
    {
    	List<Map<String, Object>> brands = brandDao.findAll();
        System.out.println(brands.size());
  
        List<Map<String, Object>> cities = cityDao.find();  
        System.out.println(cities.size());  
    }
} 
