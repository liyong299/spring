/**
 * ��Ŀ���ƣ�spring
 * �ļ�������com.ly.test.spring.ע��
 * �ļ����ƣ�Autowired.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��1��27�� ����2:42:35
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.test.spring.ע��;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ly
 *
 */
//������ʱִ��  
@Retention(RetentionPolicy.RUNTIME)

// ע��ʹ�õط�
@Target(ElementType.FIELD)
public @interface MyAutowired
{
	//ע���name����
    public String name() default "";  
}
