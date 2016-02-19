/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao1
 * 文件名称：DaoTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月15日 上午11:20:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
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
