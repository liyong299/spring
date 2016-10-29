package com.ly.test.spring.ผฬณะ;

public class Foo {
	public Foo() {
		System.out.println(" Foo()  : ");
	}
	public Foo(Object data) {
		System.out.println(" Foo(Object data)  : " + data);
	}

	//	public Foo(Object[] data) {
	//		System.out.println(" Foo(Object[] data)  : " + data);
	//	}

	public Foo(Object... data) {
		System.out.println(" Foo(Object[] data)  : " + data);
	}
}
