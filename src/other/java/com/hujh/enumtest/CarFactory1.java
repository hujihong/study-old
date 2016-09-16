package com.hujh.enumtest;

public enum CarFactory1 {
  
  FordCar, BuickCar;
  
  public Car create() {
    switch (this) {
    case FordCar:
      return new FordCar();
      // break;
    case BuickCar:
      return new BuickCar();
      // break;
    default:
      // break;
      throw new AssertionError("无效参数");
    }
  }
}


