package com.hujh.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {

  @Test
  public void test1(){
      ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
      UserService userService=(UserService)context.getBean("userService");
      userService.add();
      userService.search();
  }
  
}
