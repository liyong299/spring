/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.other
 * �ļ����ƣ�Test.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��1�� ����1:42:26
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
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
		System.out.println("Person�������л��ɹ���");
		
		byte[] bs = FileUtil.readAsByteArray(new File("aa.txt"));
		
	}

}
