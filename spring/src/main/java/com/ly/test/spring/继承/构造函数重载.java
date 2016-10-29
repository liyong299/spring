/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.继承
 * 文件名称：构造函数重载.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年10月23日 上午11:55:35
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.继承;

/**
 * @功能描述：
 * @文件名称：构造函数重载.java
 * @author ly
 */
public class 构造函数重载 {

	public 构造函数重载(String ab, Object cd) {
		System.out.println(ab + "-------2------" + cd);
	}

	public 构造函数重载(String ab, String cd) {
		System.out.println(ab + "-------1------" + cd);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new CB("BB", "AA");
	}
}

class PA {
	public PA() {};

	public PA(String p1, Object p2) {
		System.out.println(p1 + "-------4------" + p2);
	}
}

class CB extends PA {
	public CB(String p1, String p2) {
		System.out.println(p1 + "-------3------" + p2);
	}

}