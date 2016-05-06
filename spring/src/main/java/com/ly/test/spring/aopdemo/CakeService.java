package com.ly.test.spring.aopdemo;

import org.springframework.stereotype.Component;

@Component
public class CakeService {

    public static void main(String[] args) {
	
    }

    public void test(String abc)
    {
	System.out.println(abc);
    }
    public void 烤面包()
    {
	准备原料();
	烤制();
	取出();
    }
    public void 准备原料()
    {
	System.out.println("准备原料");
    }
    public void 烤制()
    {
	System.out.println("烤制");
    }
    public void 取出()
    {
	System.out.println("取出");
    }
}
