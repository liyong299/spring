/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao3.util
 * 文件名称：ShardServer.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午5:38:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spring.dao3.util.shard.DefaultRule;
import spring.dao3.util.shard.ShardFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述：<p color="red">提供分库分表处理器服务</p>
 * 文件名称：ShardServer.java
 * @author ly
 */
public class ShardServer
{
	 private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ShardServer()
	{
		
	}
	
	/**
	 * 功能描述：
	 * 实现逻辑：
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		Properties prop = new Properties();
		
		String path = "D:/Work/Workspace/other/spring/spring.dao/src/main/resources/config/shard.properties";
		prop.load(new FileInputStream(new File(path)));
		String shard = prop.getProperty("shard.table");
		System.out.println(shard);
		JSONArray jsonArray = JSONArray.parseArray(shard);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		System.out.println(jsonObject.get("orgTab"));
		System.out.println(jsonObject.get("rule"));
		System.out.println(jsonObject.get("db"));
		System.out.println(jsonObject.get("tabRule"));
	}
	
	/**
	 * <p>功能描述：根据原始表名，获取分库分表后的表名</p>
	 * <p>实现逻辑：首先根据表名获取分库分表规则，计算出库名；然后，根据参数值获取表名</p>
	 * @param orgTableName 原始表名
	 * @param params 分库分表需要的参数信息
	 * @return
	 */
	public String[] getTableName(String orgTableName, Map<String, String> params)
	{
		JSONObject ruleObject = ShardConfig.getInstall().getJsonObject(orgTableName);
		String ruleName = ruleObject.getString("rule");
		DefaultRule ruleExecutor = ShardFactory.create(ruleName);
		
		String[] dbInfo = ruleExecutor.execute(params, ruleObject);
		if (dbInfo == null)
		{
			 if (logger.isWarnEnabled()) logger.warn("计算表的Shrd规则失败, 参数：" + orgTableName + "||||" + params);
		}
		else
		{
			 if (logger.isInfoEnabled()) logger.info("计算表的Shrd规则成功，结果是：[" + dbInfo[0] + "====" + dbInfo[1] +
					"], 参数：" + orgTableName + "||||" + params);
		}
		return dbInfo;
	}
}
