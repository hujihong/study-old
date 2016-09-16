package com.hujh.enumtest;

public class CarFactory {
  
  public static Car createCar(Class<? extends Car> c) {
    try {
      return (Car) c.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
