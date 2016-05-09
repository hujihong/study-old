package com.hujh.junit;

public class Main {

  Integer i = 0;
  public static void main(String[] args) {
    
    Main main = new Main();
    System.out.println(main.i);
    main.test(main.i);
    System.out.println(main.i);
  }
  
  public void test(Integer y) {
    y = 100;
  }
  
  
  
}
