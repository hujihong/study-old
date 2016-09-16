package com.hujh.ex;

import java.util.concurrent.Callable;

public class TaxCalculator implements Callable<Integer> {

  // 本金
  private int seedMoney;
  
  public TaxCalculator(int _seedMoney) {
    seedMoney = _seedMoney;
  }
  
  @Override
  public Integer call() throws Exception {
    // 复杂计算
    try {
      Thread.sleep(10*1000); // 10秒
    } catch (Exception e) {
      e.printStackTrace();
    }
    return seedMoney/10;
  }

}
