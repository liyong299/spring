/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao3.util.shard
 * 文件名称：ByModAndMonth.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午6:46:58
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao3.util.shard;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import spring.dao3.util.DateUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：ByModAndMonth.java
 * @author ly
 */
public class ByModAndMonth extends DefaultRule
{
	
	/**
	 * 功能描述：
	 * 实现逻辑：
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * 根据配置的规则rule，和传入的map，判断当前操作对应的DB信息和Table信息
	 * @see spring.dao3.util.shard.DefaultRule#execute(java.util.Map, com.alibaba.fastjson.JSONObject)
	 */
	@Override
	public String[] execute(Map<String, String> params, JSONObject ruleObject)
	{
		
		String dbshardfield = ruleObject.getString("dbshardfield");
		String mod = params.get(dbshardfield);
		String divider = ruleObject.getString("divider");
		
		String tabshardfield = ruleObject.getString("tabshardfield");
		String dateStr = params.get(tabshardfield);
		
		if (mod == null || "".equals(mod) || "0".equals(mod) || !mod.matches("\\d+"))
		{
			return null;
		}
		
		if (divider == null || "".equals(divider) || !divider.matches("\\d+"))
		{
			return null;
		}
		
		String[] shardInfo = new String[2];
		
		JSONObject jsonObject = ruleObject;
		String table_prefix = jsonObject.getString("dbPrefix");
		
		mod = mod.substring(mod.length() - 6, mod.length());
		String dbInfo = table_prefix + Integer.valueOf(mod) % Integer.valueOf(divider);
		shardInfo[0] = dbInfo;
		try
		{
			Date date = DateUtil.parse(dateStr);
			String yyyyMM = DateUtil.format(date, DateUtil.yyyyMM);
			String tableInfo = jsonObject.getString("tabPrefix") + yyyyMM;
			shardInfo[1] = tableInfo;
			return shardInfo;
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return shardInfo;
	}

}
