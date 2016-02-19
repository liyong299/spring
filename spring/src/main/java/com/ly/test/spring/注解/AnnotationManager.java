/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.注解
 * 文件名称：AnnotationManager.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 下午2:54:10
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.注解;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 加载配置的bean，并扫描bean的描述，处理器注解。
 * @author ly
 *
 */
public class AnnotationManager
{
	// 保存配置的对象
	private Map<String, String> annoConfig = new HashMap<String, String>();
	
	// 保存加载到VM的对象
	private Map<String, Object> objs = new HashMap<String, Object>();
	
	// 保存加载到VM的对象,方便AOP注解
	private Map<Class, Object> clsObjs = new HashMap<Class, Object>();
	
	public AnnotationManager(Properties prop)
	{
		readProp(prop);
		
		initObjects();
		
		annotationInject();
	}
	
	/**
	 * 处理每个对象的注解
	 */
	private void annotationInject()
	{
		for (Map.Entry<String, Object> entry : this.objs.entrySet())
		{
			fieldAnnotation(entry.getValue());
		}
	}
	
	/**
	 * 处理单个对象的注解
	 * @param value
	 */
	private void fieldAnnotation(Object bean)
	{
		try
		{
			Class cls = bean.getClass();
			PropertyDescriptor[] pds = Introspector.getBeanInfo(cls).getPropertyDescriptors();
			
			for (PropertyDescriptor pd : pds)
			{
				System.out.println("属性名称： " + pd.getDisplayName() + " | " + pd.getName());
				// 判断setter方法是否存在，通过setter方法，将对象设置
				Method setter = pd.getWriteMethod();
				if (setter == null)
				{
					System.out.println("当前属性，没有setter方法！");
				}
				else
				{
					System.out.println(cls.getDeclaredFields().length);
					Field field = null;
					
					for (Field f : cls.getDeclaredFields())
					{
						if (pd.getName().equals(f.getName()))
						{
							field = f;
						}
					}
					
					if (field != null)
					{
						Object obj = null;
						if (field.isAnnotationPresent(MyAutowired.class))
						{
							System.out.println("包含注解的字段：" + pd.getName());
							MyAutowired anno = field.getAnnotation(MyAutowired.class);
							if (anno.name() == null || "".equals(anno.name()))
							{
								System.out.println("注解名称缺少,则从配置中获取，字段名称一样的");
								obj = this.objs.get(pd.getName());
							}
							else
							{
								obj = this.objs.get(anno.name());
							}
							
							if (field.isAccessible())
							{
								field.set(bean, obj);
							}
							else
							{
								setter.invoke(bean, obj);
							}
						}
						else
						{
							System.out.println("当前属性，没有注解！");
						}
					}
					else
					{
						System.out.println("字段存在异常！");
					}
				}
			}
		}
		catch (IntrospectionException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 初始化配置的每个类
	 */
	private void initObjects()
	{
		for (Map.Entry<String, String> entry : this.annoConfig.entrySet())
		{
			try
			{
				System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
				
				Object obj = Class.forName(entry.getValue()).newInstance();
				this.objs.put(entry.getKey(), obj);
				
				this.clsObjs.put(obj.getClass(), obj);
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}
	private void readProp(Properties prop)
	{
		for (Entry<Object, Object> obj : prop.entrySet())
		{
			this.annoConfig.put(obj.getKey().toString(), obj.getValue().toString());
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		String filePath = "D:\\Work\\Workspace\\scec_dis\\spring\\src\\main\\resources\\anno.properties";
		prop.load(new FileInputStream(new File(filePath)));
		
		AnnotationManager annot = new AnnotationManager(prop);
		UserAction userAction = (UserAction) annot.getBean("userAction");
		userAction.life();
	}

	public Object getBean(String beanName)
	{
		return this.objs.get(beanName);
	}
	
	public Object getBeanByClass(Class cls)
	{
		return this.clsObjs.get(cls);
	}

	/**
	 * @return the objs
	 */
	public Map<String, Object> getObjs()
	{
		return objs;
	}

	/**
	 * @param objs the objs to set
	 */
	public void setObjs(Map<String, Object> objs)
	{
		this.objs = objs;
	}
}
