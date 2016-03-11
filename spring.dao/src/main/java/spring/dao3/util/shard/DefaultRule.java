/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao3.util.shard
 * 文件名称：DefaultRule.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午6:47:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao3.util.shard;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述：<p color="red">分库分表默认规则</p>
 * 文件名称：DefaultRule.java
 * @author ly
 */
public class DefaultRule
{
	/**
	 * 功能描述：<p>根据传入的参数params和配置的规则ruleObject计算实际库名和表名</p>
	 * 实现逻辑：<p>根据不同的实现类，实现不同的业务逻辑，默认规则不做任何实现</p>
	 * @param params
	 * @param ruleObject
	 * @return 数组，长度为2，第一个是DB数据源的spring名称，第二个是DB表的名称
	 */
	public String[] execute(Map<String, String> params, JSONObject ruleObject)
	{
		return null;
	}
	/**
	 * 功能描述：
	 * 实现逻辑：
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
