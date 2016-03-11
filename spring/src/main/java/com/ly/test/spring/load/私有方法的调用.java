/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.load
 * 文件名称：SayHello.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月19日 下午3:21:48
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.load;

/**
 * @author ly
 *
 */
public class 私有方法的调用
{
	public static void main(String[] args) throws ClassNotFoundException, Exception
	{
		Class cls = Class.forName("com.ly.test.spring.load.私有方法的调用");
		Object obj = cls.newInstance();
		cls.getDeclaredMethod("runPrivate",null).invoke(obj, null);
	}
	
	private void runPrivate()
	{
		System.out.println("这是一个私有方法");
	}
}
