/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.exception
 * 文件名称：测试异常处理.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年7月25日 下午4:19:19
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.exception;

import org.springframework.stereotype.Service;

/**
 * @功能描述：
 * @文件名称：测试异常处理.java
 * @author ly
 */
@Service
public class ExcetptionHandlerTest {

	/**
	 * 异常拦截处理。 只能用在web中，应该是和servlet的某些注解相关
	 * 
	 * @param request
	 *            HTTP请求
	 * @param ex
	 *            异常
	 * @return 返回异常视图。
	 */
	/*
	 * @ExceptionHandler(value = { BindException.class, Exception.class })
	 * protected void checkedException(String tenantKey, String bodyJson,
	 * Exception ex) { System.out.println("+++++++++++++++++++++++++");
	 * System.out.println("测试异常处理.checkedException"); }
	 */

	public void testException() {
		System.out.println("测试异常处理.testException");
		throw new RuntimeException("---------");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
