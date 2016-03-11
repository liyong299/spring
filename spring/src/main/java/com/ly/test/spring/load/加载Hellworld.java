/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.load
 * 文件名称：加载Hellworld.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月19日 下午2:21:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.load;

import java.io.File;
import java.lang.reflect.Method;

import org.aspectj.util.FileUtil;

/**
 * @author ly
 *
 */
public class 加载Hellworld
{

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, Exception
	{
		ClassLoader classLoader = 加载Hellworld.class.getClassLoader();
		System.out.println(classLoader);
		System.out.println(classLoader.getParent());
		System.out.println(classLoader.getParent().getParent());
		
		// 需要加载的文件路径，当文件不在当前classes目录下，用该方法。
		String clsPath = "D:/Work/Workspace/other/dup/target/classes/HelloWorld.class";
		Class cls = new FileClassLoader().load("HelloWorld", clsPath);
		
		Class cls1 = new FileClassLoader().load("HelloWorld", new File(clsPath));  // 加载两遍不会报错
		
		Object object = cls.newInstance();
		Method method = cls.getMethod("testLoader", null);
		method.invoke(object, null);
		
		// 自定义的类加载器 没有覆盖findClass方法
		ClassLoader myClassLoader = cls.getClassLoader();
		
		// 直接将class文件拷贝到classes目录下，按照如下方法加载。
		Class cls2 = Class.forName("HelloWorld", true, classLoader);
		Object object2 = cls2.newInstance(); 	
		Method method2 = cls2.getMethod("testLoader", null);
		method2.invoke(object2, null);
		
		// 直接将class文件拷贝到classes目录下，按照如下方法加载。
		Class cls3 = classLoader.loadClass("HelloWorld");
		Object obj3 = cls3.newInstance();
		cls3.getMethod("testLoader", null).invoke(obj3, null);
		
		// 其他类中不能访问
		Class cls4 = Class.forName("com.ly.test.spring.load.私有方法的调用");
		Object obj4 = cls4.newInstance();
		cls4.getDeclaredMethod("runPrivate",null).invoke(obj4, null);
	}
}
