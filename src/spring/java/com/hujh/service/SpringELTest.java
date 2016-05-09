package com.hujh.service;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringELTest {

  @Test
  public void test1(){
    ExpressionParser parser= new SpelExpressionParser();
    Expression exp=parser.parseExpression("'HelloWorld'.concat('!')");
    String message=(String)exp.getValue();
    System.out.println(message);
  }
  
  // 下面的String属性Bytes可以被调用作为调用JavaBean属性的一个例子
  @Test
  public void test3(){
    ExpressionParser parser= new SpelExpressionParser();
    //invokes'getBytes()'
    // Expression exp=parser.parseExpression("'HelloWorld'.bytes");
    Expression exp=parser.parseExpression("'HelloWorld'.getBytes()");
    byte[] bytes=(byte[])exp.getValue();
    // "".getBytes()
    
  }
  
  // SpEL使用标准的‘.’符号支持属性嵌套和属性设值，例如：prop1.prop2.prop3.
  // 公共属性也可以被访问
  @Test
  public void test2() {
    ExpressionParser parser= new SpelExpressionParser();
    //invokes'getBytes().length'
    // Expression exp=parser.parseExpression("'HelloWorld'.bytes.length");
    // Expression exp=parser.parseExpression("'HelloWorld'.getBytes().length");
    Expression exp=parser.parseExpression("'HelloWorld'.getBytes('UTF-8').length");
    int length=(Integer)exp.getValue();
    System.out.println(length);
  }
  
  @Test
  public void test4() {
    // 使用字符串构造器而不是字符串文字。
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("new String('helloworld').toUpperCase()");
    String message = exp.getValue(String.class);
    System.out.println(message);
  }
  
}
