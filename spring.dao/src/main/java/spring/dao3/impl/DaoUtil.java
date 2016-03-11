/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao3.impl
 * 文件名称：DaoUtil.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月29日 下午2:44:01
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao3.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 在DAO和DB之间，增加一层，用来做SQL路由，指定不同的DB
 * @author ly
 *
 */
@Repository
public class DaoUtil
{
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 功能描述：<p>方法功能</p>
     * 实现逻辑：<p>实现步骤</p>
     * @param dsInfo 参数第一个必须是datasource名称
     * @param sql
     * @return
     */
    public List<Map<String, Object>> find(String dsInfo, String sql) 
    {
        return this.jdbcTemplate.queryForList(sql);  
    }  
    
    public List<Map<String, Object>> findAll(String sql) {
        return this.jdbcTemplate.queryForList(sql);
    }  
}
