package com.ly.test.spring.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.smart.validate.rule.RegexpValidate;

/**
 * @功能描述：方法注解中，包含各个字段的注解集成。
 * @文件名称：MethodAnnotation.java
 * @author ly
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation {

	RegexpValidate[] regex2() default {};

	/** 复杂对象的额外校验 */
	Class<? extends FieldValidateRule>[] fieldValidateClasses() default {};

	String jsonRoot() default ""; //如果是json，则该方法返回顶层json节点的名字
}
