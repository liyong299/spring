/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.注解
 * 文件名称：Autowired.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 下午2:42:35
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ly
 *
 */
//在运行时执行  
@Retention(RetentionPolicy.RUNTIME)

// 注解使用地方
@Target(ElementType.FIELD)
public @interface MyAutowired
{
	//注解的name属性
    public String name() default "";  
}
