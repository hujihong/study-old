package com.hujh.enumtest;

import java.util.ArrayList;
import java.util.List;

public class EnumMain {

  public static void main(String[] args) {
    System.out.println("The most confortable season is " + Season.getComfortableSeason());
    System.out.println("ordinal:" + Season.Autumn.ordinal());
    // Car car = CarFactory.createCar(FordCar.class);
    // Car car = CarFactory1.BuickCar.create();
    Car car = CarFactory2.FordCar.create();
    List<String> ls = new ArrayList<>();
    List<Integer> li = new ArrayList<>();
    
    System.out.println(ls.getClass() == li.getClass());
  }
  
  

}
