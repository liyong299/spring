package com.ly.test.spring.aopdemo;

import org.springframework.stereotype.Component;

@Component
public class SwimService {

    public static void main(String[] args) {
	
    }

    public void 游泳()
    {
	穿泳装();
	下水开游();
	上岸休息();
    }
    public void 穿泳装()
    {
	System.out.println("准备原料");
    }
    public void 下水开游()
    {
	System.out.println("烤制");
    }
    public void 上岸休息()
    {
	System.out.println("取出");
    }
}
