/**
 * 项目名称：spring.dao
 * 文件包名：org.opencloudb
 * 文件名称：测试类1.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午1:57:05
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package org.opencloudb;

import org.opencloudb.parser.ManagerParse;

/**
 * @author ly
 *
 */
public class 测试类1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String sql = "select * from dual";
		int rs = ManagerParse.parse(sql);
		System.out.println(Integer.toBinaryString(rs));
		System.out.println(rs & 0xff);
		
		String sql2 = "update t_test set name='1'";
		int rs2 = ManagerParse.parse(sql2);
		System.out.printf("%5d  %5d  %5s", rs2 & 0xff, rs2, "\r\n");
		
		String sql3 = "insert into t_test values('aa')";
		int rs3 = ManagerParse.parse(sql3);
		System.out.println(rs3 & 0xff);
		
		String sql4 = "delete from t_test where name='1'";
		int rs4 = ManagerParse.parse(sql4);
		System.out.println(rs4 & 0xff);
		
		System.out.println(1 & 0xff);
	}

}
