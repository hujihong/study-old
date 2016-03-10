package com.hujh.junit;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class MathDemoTest {

  @Test
  public void testAdd() {
    MathDemo demo = new MathDemo();
    int expected = 2;
    Assert.assertEquals(expected, demo.add(1, 1));
    
  }

  @Test
  public void testDiv() {
    MathDemo demo = new MathDemo();
    int expected = 2;
    Assert.assertEquals(expected, demo.div(2, 1));
  }

  @Test(expected=ArithmeticException.class)
  public void testDivByZero() {
    MathDemo demo = new MathDemo();
    int expected = 2;
    Assert.assertEquals(expected, demo.div(2, 0));
  }
  
}
