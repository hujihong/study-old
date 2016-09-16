package com.hujh;

import java.lang.reflect.Method;

import com.hujh.rel.Foo;


public class MainTest {

  public static void main(String[] args) {
    rel();

  }

  public static void rel() {
    try {
      Method method = Foo.class.getMethod("doStuff");
      System.out.println("Accessible=" + method.isAccessible());
      method.invoke(new Foo(), null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
