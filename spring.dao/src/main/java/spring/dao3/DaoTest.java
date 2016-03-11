package spring.dao3;

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

import spring.dao3.impl.TicketOrderDaoImpl;


/**
 * 
 * @author ly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app_dao3.xml")  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
public class DaoTest
{
    @Resource
    private TicketOrderDaoImpl orderDao;

    @Test
    public void testList()
    {
    	List<Map<String, Object>> orders = orderDao.find("1511231000000025", "2016-01-01");
    	System.out.println("查询结果：");
    	System.out.println(orders);
    }
} 
