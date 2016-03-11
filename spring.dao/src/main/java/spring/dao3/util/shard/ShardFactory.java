/**
 * 项目名称：spring.dao
 * 文件包名：spring.dao3.util.shard
 * 文件名称：ShardFactory.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午6:45:49
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring.dao3.util.shard;


/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：ShardFactory.java
 * 
 * @author ly
 */
public class ShardFactory
{
	public static DefaultRule create(String ruleName)
	{
		if ("byMODAndMonth".equals(ruleName))
		{
			return new ByModAndMonth();
		}
		else
		{
			return new DefaultRule();
		}
	}

	/**
	 * 功能描述： 实现逻辑：
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
