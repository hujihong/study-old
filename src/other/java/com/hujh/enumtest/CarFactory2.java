package com.hujh.enumtest;

public enum CarFactory2 {
  FordCar {

    @Override
    public Car create() {
      return new FordCar();
    }
    
  },
  BuickCar {
    @Override
    public Car create() {
      return new BuickCar();
    }
    
  };
  
  public abstract Car create();
}
