package com.ly.test.spring.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.smart.validate.rule.RegexpValidate;

/**
 * @��������������ע���У����������ֶε�ע�⼯�ɡ�
 * @�ļ����ƣ�MethodAnnotation.java
 * @author ly
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation {

	RegexpValidate[] regex2() default {};

	/** ���Ӷ���Ķ���У�� */
	Class<? extends FieldValidateRule>[] fieldValidateClasses() default {};

	String jsonRoot() default ""; //�����json����÷������ض���json�ڵ������
}
