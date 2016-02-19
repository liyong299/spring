/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.other
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月1日 下午1:42:26
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.aspectj.util.FileUtil;

/**
 * @author ly
 *
 */
public class Test
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		String content = FileUtil.readAsString(new File("aa.txt"));
		System.out.println(content);
		
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
		new File("bb.txt")));
		oo.writeObject(new Date());
		System.out.println("Person对象序列化成功！");
		
		byte[] bs = FileUtil.readAsByteArray(new File("aa.txt"));
		
	}

}
