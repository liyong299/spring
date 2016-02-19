/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao1
 * 文件名称：DaoTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月15日 上午11:20:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao1;

/**
 * @author ly
 *
 */
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import spring.dao1.one.BrandDaoImpl;
import spring.dao1.po.Brand;
import spring.dao1.po.City;
import spring.dao1.two.CityDaoImpl;

/**
 * 
 * @author ly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app_dao1.xml")  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
public class DaoTest
{
    @Resource  
    private BrandDaoImpl brandDao;  
  
    @Resource
    private CityDaoImpl cityDao;  
  
    @Test  
    public void testList() {
        List<Brand> brands = brandDao.findAll();  
        System.out.println(brands.size());  
  
        List<City> cities = cityDao.find();  
        System.out.println(cities.size());  
    }  
} 
