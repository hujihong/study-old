package com.hujh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MainTest {

  public static void main(String[] args) {
    
    Subject subject = new RealSubject();
    InvocationHandler handler = new SubjectHandler(subject);
    // 当前加载器
    ClassLoader classLoader = subject.getClass().getClassLoader();
    //  动态代理
    Subject proxy = (Subject) Proxy.newProxyInstance(classLoader, subject.getClass().getInterfaces(), handler);
    // 执行
    proxy.request();
    
    proxy1();
    
  }

  
  public static void proxy1() {
    Animal jerry = new Rat();
    // 增加飞行能力
    jerry = new DecorateAnimal(jerry, FlyFeature.class);
    // 增加钻地能力
    jerry = new DecorateAnimal(jerry, DigFeature.class);
    jerry.doStuff("12");
    
  }
}
