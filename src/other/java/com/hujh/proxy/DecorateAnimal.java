package com.hujh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class DecorateAnimal implements Animal {

  // 被包装的动物 
  private Animal animal;
  
  // 使用哪 一个包装器
  private Class<? extends Feature> clz;
  
  public DecorateAnimal(Animal _aAnimal, Class<? extends Feature> _clz){
    animal = _aAnimal;
    clz = _clz;
  }
  
  @Override
  public void doStuff(String name) {
    InvocationHandler handler = new InvocationHandler() {
      
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        if(Modifier.isPublic(method.getModifiers())) {
          object = method.invoke(clz.newInstance(), args);
        }
        animal.doStuff(name);
        return object;
      }
    };
    
    // 当前加载器
    ClassLoader classLoader = getClass().getClassLoader();
    // 动态代理
    Feature proxy = (Feature) Proxy.newProxyInstance(classLoader, clz.getInterfaces(), handler);
    proxy.load();
  }

}
