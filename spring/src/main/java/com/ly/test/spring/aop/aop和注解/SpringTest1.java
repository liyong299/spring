package com.ly.test.spring.aop.aop��ע��;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.ly.test.spring.ע��.AnnotationManager;
import com.ly.test.spring.ע��.MyAutowired;

/**
 * ��֮ǰע��Ļ����ϣ�����AOP�Ĺ��ܡ�
 * @author ly
 *
 */
public class SpringTest1
{
	private AnnotationManager annotationManager;
	
	/**
	 * ����AOP�������ļ�
	 */
	private Map<String, String> aopConfig = new HashMap<String, String>();
	// �������õĶ���
	private Map<String, String> annoConfig = new HashMap<String, String>();
	
	// ������ص�VM�Ķ���
	private Map<String, Object> objs = new HashMap<String, Object>();
	
	public SpringTest1(String annoFile, String aopFile)
	{
		Properties annoProp = new Properties();
		Properties aopProp = new Properties();
		
		try
		{
			annoProp.load(new FileInputStream(new File(annoFile)));
			aopProp.load(new FileInputStream(new File(aopFile)));
			
			// 1�� ��ȡAOP��ע�������
			readConfig(aopProp, annoProp);
			
			// 2�� ��ȡAOP��ע�������
			initObjects();
			
			// 3������AOP
			initAop();
			
			// 4������ע��
			annotationInject();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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
	
	/**
	 * ��ȡ�����ļ�������Map�У��������
	 * @param aopProp
	 */
	private void readConfig(Properties aopProp, Properties annoProp)
	{
		for (Entry<Object, Object> obj : aopProp.entrySet())
		{
			this.aopConfig.put(obj.getKey().toString(), obj.getValue().toString());
		}
		
		for (Entry<Object, Object> obj : annoProp.entrySet())
		{
			this.annoConfig.put(obj.getKey().toString(), obj.getValue().toString());
		}
	}
	
	/**
	 * ����AOP�������õ�AOP�����޸�ΪProxy��
	 */
	private void initAop()
	{
		for (Map.Entry<String, String> entry : this.aopConfig.entrySet())
		{
			System.out.println(MessageFormat.format("��ǰ�����AOP���á�{0}������ҪAOP���ࡾ{1}��", entry.getKey(), entry.getValue()));
			Class cls = null;
			try
			{
				// ��ȡ��ǰ��Ҫ��AOP�Ķ����Class
				cls = Class.forName(entry.getKey());
				
				Object obj = null;
				String key = null;
				
				// �������ң��ö��󱻳�ʼ����ʵ��
				for (Map.Entry<String, Object> annObj : objs.entrySet())
				{
					Object tmpObj = annObj.getValue();
					System.out.println(MessageFormat.format("��ǰ�������ࡾ{0}������ҪAOP���ࡾ{1}��", tmpObj.getClass(), cls));
					if (tmpObj.getClass().isAssignableFrom(cls))
					{
						obj = annObj.getValue();
						key = annObj.getKey();
						break;
					}
				}
				
				if (obj != null)
				{
					System.out.println(MessageFormat.format("��Ҫ������Ķ���{0}��", obj));
					
					// ʵ��������������
					Constructor constructor = Class.forName(entry.getValue()).getConstructor(new Object().getClass());
					Object proxy = constructor.newInstance(obj);
					
					// ���»�ȡloader�еĶ���
					Object newObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(),
							obj.getClass().getInterfaces(), (InvocationHandler) proxy);
					
					// ���½�������뵽Map�С�
					System.out.println(MessageFormat.format("���´����Ķ������{0}����ֵ��{1}��", key, newObj));
					this.objs.put(key, newObj);
				}
				else
				{
					System.out.println(MessageFormat.format("����[{0}]û������bean�����ܱ�AOP", cls));
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}
	
	public Object getBean(String beanName)
	{
		return this.objs.get(beanName);
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
