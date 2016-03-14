package com.hujh.service;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterLog implements AfterReturningAdvice{
    /**
     * returnValue:返回值类型
     * method:被调用的方法对象
     * arg1:被调用的方法的参数
     * target:被调用方法的目标对象 
     */
    @Override
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        
        System.out.println(target.getClass().getName()+"中的"+method.getName()+"方法被执行成功,返回值是"+returnValue);
        
 }
}