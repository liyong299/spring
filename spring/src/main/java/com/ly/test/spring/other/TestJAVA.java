/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.other
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月1日 下午1:42:26
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.other;

import org.junit.Test;


/**
 * @author ly
 *
 */

public class TestJAVA
{
	@Test
	public void Test执行顺序() {
		try{
			System.out.println("代码0");
			throw new Exception("代码1");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("代码2");
			throw new RuntimeException("代码22");
		} finally {
			System.out.println("代码3");
		}
	}

	public static void main(String[] ar) throws Exception {
		try {
			System.out.println("代码0");
			throw new Exception("代码1");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("代码2");
			//			throwException();
			throw new Exception(ex);
		} finally {
			System.out.println("代码3");
		}
	}

	public static void throwException() {
		throw new RuntimeException("代码22");
	}
}
