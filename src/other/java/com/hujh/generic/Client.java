package com.hujh.generic;

import java.util.Scanner;

public class Client {

  public static void main(String[] args) {
   Base base = new Base();
   base.fun(100, 50);
   
   Sub sub = new Sub();
   sub.fun(100, 50);
   
   // devidend();
   System.out.println(remainder(-1, 2));
   System.out.println(Integer.MAX_VALUE);
   System.out.println(Math.pow(2, 24));
   
   Integer integer = new Integer(1000);
   Integer integer2 = new Integer(1000);
   System.out.println("integer:" + integer.equals(integer2));
   
   Long i = new Long(100000);
   Long i2 = new Long(100000);
   System.out.println("Long:" + i.equals(i2));
   
   long y = 1000000;
   long y2 = 1000000;
   System.out.println("long:" + (y == y2));
  }
  
  public static void devidend() {
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入多个数判断奇偶：");
    while(scanner.hasNextInt()) {
      int i = scanner.nextInt();
      String string = i + "->" + (i%2 == 1 ? "奇数" : "偶数");
      System.out.println(string);
    }
    
  }

  public static int remainder(int devidend, int divisor) {
    //      -1 - -1/2 * 2 
    //      1 - 1/2 *2
    //      4 - 4/2 *2
    //      -2 - -2/2 *2 
            
    return devidend - devidend / divisor * divisor;
  }
}

class Base {
  void fun(int price, int... discounts) {
    System.out.println("base...");
  }
}

class Sub extends Base {
  @Override
  void fun(int price, int... discounts) {
    System.out.println("sub...");
  }
}

