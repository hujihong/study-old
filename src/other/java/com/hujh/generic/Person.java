package com.hujh.generic;

import java.io.Serializable;

import org.springframework.web.bind.annotation.InitBinder;

public class Person implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 6891222482946189795L;
  
  public final String aString;
  
  public final String bString = initbstring();
  
  public String initbstring() {
    return "混世魔王22";
  }
  
  public Person() {
    aString = "混世魔王";
  }
  
  private String name;
  private int age;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person [name=" + name + ", age=" + age + "]";
    // return "Person [name=" + name + "]";
  }
  
  
}
