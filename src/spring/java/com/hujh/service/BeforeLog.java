package com.hujh.service;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeLog  implements MethodBeforeAdvice{

    /***
     * method:被调用的方法对象
     * arg1:被调用的方法的参数
     * target:被调用方法的目标对象 
     */
    @Override
    public void before(Method method, Object[] arg1, Object target)
            throws Throwable {
        System.out.println(target.getClass().getName()+"中的"+method.getName()+"方法被执行");
    }

}
