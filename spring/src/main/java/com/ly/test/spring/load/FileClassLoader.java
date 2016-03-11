/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.load
 * �ļ����ƣ�FileClassLoader.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��19�� ����2:50:16
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.load;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.aspectj.util.FileUtil;

import sun.misc.VM;

/**
 * @author ly
 * 
 */
public class FileClassLoader extends ClassLoader
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	
	/**
	 * true if the name is null or has the potential to be a valid binary name
	 * �÷���������ClassLoader����������Ƿ���ȷ
	 * 
	 * @param name
	 * @return
	 */
	private boolean checkName(String name)
	{
		if ((name == null) || (name.length() == 0))
			return true;
		if ((name.indexOf('/') != -1) || (!VM.allowArraySyntax() && (name.charAt(0) == '[')))
			return false;
		return true;
	}

	/**
	 * �����ļ���������������һ��class
	 * 
	 * @param file
	 * @throws IOException
	 */
	public Class load(String clsName, File file) throws IOException
	{
		if (!checkName(clsName))
		{
			return null;
		}
		Class c = findLoadedClass(clsName);
		if (c == null)
		{
			byte[] clsData = FileUtil.readAsByteArray(file);
			return this.defineClass(clsName, clsData, 0, clsData.length);
		}
		return c;
	}

	/**
	 * �����ļ�·��������������һ��class
	 * 
	 * @param file
	 * @throws IOException
	 */
	public Class load(String clsName, String filePath) throws IOException
	{
		return load(clsName, new File(filePath));
	}


	/**
	 * �����ǰ��������͸���������ж�����������࣬���ܳ��쳣��
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class<?> findClass(String clsName) throws ClassNotFoundException
	{
		Class c = findLoadedClass(clsName);
		if (c == null)
		{
			throw new ClassNotFoundException("�಻���ڣ�" + clsName);
		}
		return c;
	}

}
