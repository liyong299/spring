/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.ע��
 * �ļ����ƣ�AnnotationManager.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����2:54:10
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.ע��;

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
 * �������õ�bean����ɨ��bean��������������ע�⡣
 * @author ly
 *
 */
public class AnnotationManager
{
	// �������õĶ���
	private Map<String, String> annoConfig = new HashMap<String, String>();
	
	// ������ص�VM�Ķ���
	private Map<String, Object> objs = new HashMap<String, Object>();
	
	// ������ص�VM�Ķ���,����AOPע��
	private Map<Class, Object> clsObjs = new HashMap<Class, Object>();
	
	public AnnotationManager(Properties prop)
	{
		readProp(prop);
		
		initObjects();
		
		annotationInject();
	}
	
	/**
	 * ����ÿ�������ע��
	 */
	private void annotationInject()
	{
		for (Map.Entry<String, Object> entry : this.objs.entrySet())
		{
			fieldAnnotation(entry.getValue());
		}
	}
	
	/**
	 * �����������ע��
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
				System.out.println("�������ƣ� " + pd.getDisplayName() + " | " + pd.getName());
				// �ж�setter�����Ƿ���ڣ�ͨ��setter����������������
				Method setter = pd.getWriteMethod();
				if (setter == null)
				{
					System.out.println("��ǰ���ԣ�û��setter������");
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
							System.out.println("����ע����ֶΣ�" + pd.getName());
							MyAutowired anno = field.getAnnotation(MyAutowired.class);
							if (anno.name() == null || "".equals(anno.name()))
							{
								System.out.println("ע������ȱ��,��������л�ȡ���ֶ�����һ����");
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
							System.out.println("��ǰ���ԣ�û��ע�⣡");
						}
					}
					else
					{
						System.out.println("�ֶδ����쳣��");
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
	 * ��ʼ�����õ�ÿ����
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
