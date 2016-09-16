package com.hujh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectHandler implements InvocationHandler {

  private Subject subject;
  public SubjectHandler(Subject _subject) {
    subject = _subject;
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before");
    Object object = method.invoke(subject, args);
    System.out.println("after");
    return object;
  }

}
