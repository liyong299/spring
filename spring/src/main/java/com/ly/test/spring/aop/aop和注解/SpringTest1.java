package com.ly.test.spring.aop.aop和注解;

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

import com.ly.test.spring.注解.AnnotationManager;
import com.ly.test.spring.注解.MyAutowired;

/**
 * 在之前注解的基础上，集成AOP的功能。
 * @author ly
 *
 */
public class SpringTest1
{
	private AnnotationManager annotationManager;
	
	/**
	 * 保存AOP的配置文件
	 */
	private Map<String, String> aopConfig = new HashMap<String, String>();
	// 保存配置的对象
	private Map<String, String> annoConfig = new HashMap<String, String>();
	
	// 保存加载到VM的对象
	private Map<String, Object> objs = new HashMap<String, Object>();
	
	public SpringTest1(String annoFile, String aopFile)
	{
		Properties annoProp = new Properties();
		Properties aopProp = new Properties();
		
		try
		{
			annoProp.load(new FileInputStream(new File(annoFile)));
			aopProp.load(new FileInputStream(new File(aopFile)));
			
			// 1、 读取AOP和注解的配置
			readConfig(aopProp, annoProp);
			
			// 2、 读取AOP和注解的配置
			initObjects();
			
			// 3、处理AOP
			initAop();
			
			// 4、处理注解
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
	 * 读取配置文件，放入Map中，方便遍历
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
	 * 处理AOP，将配置的AOP对象修改为Proxy。
	 */
	private void initAop()
	{
		for (Map.Entry<String, String> entry : this.aopConfig.entrySet())
		{
			System.out.println(MessageFormat.format("当前处理的AOP配置【{0}】和需要AOP的类【{1}】", entry.getKey(), entry.getValue()));
			Class cls = null;
			try
			{
				// 获取当前需要被AOP的对象的Class
				cls = Class.forName(entry.getKey());
				
				Object obj = null;
				String key = null;
				
				// 遍历查找，该对象被初始化的实例
				for (Map.Entry<String, Object> annObj : objs.entrySet())
				{
					Object tmpObj = annObj.getValue();
					System.out.println(MessageFormat.format("当前遍历的类【{0}】和需要AOP的类【{1}】", tmpObj.getClass(), cls));
					if (tmpObj.getClass().isAssignableFrom(cls))
					{
						obj = annObj.getValue();
						key = annObj.getKey();
						break;
					}
				}
				
				if (obj != null)
				{
					System.out.println(MessageFormat.format("需要被代理的对象【{0}】", obj));
					
					// 实例化拦截器对象
					Constructor constructor = Class.forName(entry.getValue()).getConstructor(new Object().getClass());
					Object proxy = constructor.newInstance(obj);
					
					// 重新获取loader中的对象
					Object newObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(),
							obj.getClass().getInterfaces(), (InvocationHandler) proxy);
					
					// 重新将对象加入到Map中。
					System.out.println(MessageFormat.format("重新代理后的对象键【{0}】和值【{1}】", key, newObj));
					this.objs.put(key, newObj);
				}
				else
				{
					System.out.println(MessageFormat.format("对象[{0}]没有配置bean，不能被AOP", cls));
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
