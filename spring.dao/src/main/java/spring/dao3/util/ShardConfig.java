/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao3.util
 * 文件名称：TableConfig.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午5:03:58
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：TableConfig.java
 * 
 * @author ly
 */
public class ShardConfig
{
	Map<String, JSONObject> rulesConfig = null;
	
	public static ShardConfig tableConfig = new ShardConfig();
	
	public static ShardConfig getInstall()
	{
		return tableConfig;
	}
	
	private ShardConfig()
	{
		Properties prop = new Properties();
		System.out.println(getClass().getResource("/").getPath());
		System.out.println(ClassLoader.getSystemResource(""));
		String path = getClass().getResource("/").getPath() + "config/shard.properties";
		path = "D:/Work/Workspace/other/spring/spring.dao/src/main/resources/config/shard.properties";
		
		try
		{
			prop.load(new FileInputStream(new File(path)));
			String shard = prop.getProperty("shard.table");
			rulesConfig = new HashMap<String, JSONObject>();
			
			JSONArray jsonArray = JSONArray.parseArray(shard);
			for (int i = 0; i < jsonArray.size(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				rulesConfig.put(jsonObject.getString("orgTab"), jsonObject);
			}
			System.out.println(rulesConfig);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the jsonObject
	 */
	public JSONObject getJsonObject(String orgTab)
	{
		System.out.println("orgTable : " + orgTab);
		return this.rulesConfig.get(orgTab);
	}
	
}