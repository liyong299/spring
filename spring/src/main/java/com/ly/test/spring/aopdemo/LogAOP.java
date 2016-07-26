package com.ly.test.spring.aopdemo;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAOP {  
    
    @Pointcut("execution(* com.mopon.rmp.client.action.SwimService.*(..))")
    public void recordLog(){}
    
    @Pointcut("execution(* com.ly.test.spring.aopdemo.CakeService.*(..))")
    public void recordLog2(){}

    @Before("recordLog()")
    public void before() {  
        System.out.println("before someting to do,logging.....");  
    }  
    
    @After("recordLog()")
    public void after() {  
        System.out.println("after someting to do,logging.....");  
    }  
  
    @Around("recordLog()")
    public void around(ProceedingJoinPoint point) throws Throwable {
	System.out.println("已经记录下操作日志@Around 方法执行前");
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" + 
                point.getSignature().getDeclaringTypeName() + 
                "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" + 
                Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
        long startTime = System.nanoTime();  
        
        point.proceed();
        
        long endTime = System.nanoTime();  
        System.out.println("执行时间为： [" + (endTime - startTime) / 1000000 + "]毫秒");  
    }
    
    @Around("recordLog2()")
    public void around2(ProceedingJoinPoint point) throws Throwable {
	System.out.println("已经记录下操作日志@Around 方法执行前");
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" + 
                point.getSignature().getDeclaringTypeName() + 
                "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" + 
                Arrays.toString(point.getArgs()));
        String targetMethodName = point.getSignature().getName();
        if ("test".equals(targetMethodName))
        {
            String paramVal = point.getArgs()[0].toString();
            System.out.println("----------------------------" + paramVal);
        }
        
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
        long startTime = System.nanoTime();  
        
        point.proceed();
        
        long endTime = System.nanoTime();  
        System.out.println("执行时间为： [" + (endTime - startTime) / 1000000 + "]毫秒");  
    }
}  