package spring.dao3.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import spring.dao3.util.ShardServer;
  
@Repository  
public class TicketOrderDaoImpl  
{
    @Resource
    private DaoUtil daoUtil;
    private final String orgTab_TicketOrder = "CEC_TicketOrder";
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * <p>功能描述：<p>根据编号，查询订单</p></p>
     * <p>实现逻辑：<p>首先根据分库分表规则，查询订单所属的库和表，然后通过JDBCTemplate查询数据</p></p>
     * @param code
     * @param createTime
     * @return
     */
    public List<Map<String, Object>> find(String code, String createTime)
    {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("code", code);
    	params.put("createTime", createTime);
    	
        String[] dbInfo = new ShardServer().getTableName(orgTab_TicketOrder, params);
        
        if (logger.isInfoEnabled()) logger.info("当前DataSource信息：" + dbInfo[0] + "，当前表信息：" + dbInfo[1]);
        String sql = "select * from {0} where code={1} " ;
        
        sql = MessageFormat.format(sql, new Object[]{dbInfo[1], code, createTime});
        if (logger.isInfoEnabled()) logger.info("将要执行的SQL语句：" + sql);
        
        return daoUtil.find(dbInfo[0], sql);
    }  
}  