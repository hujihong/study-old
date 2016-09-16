package com.hujh.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import jodd.datetime.TimeUtil;

public class Runner implements Callable<Integer> {

  private CountDownLatch begin;
  private CountDownLatch end;
  public Runner(CountDownLatch begin, CountDownLatch end) {
    this.begin = begin;
    this.end = end;
  }

  @Override
  public Integer call() throws Exception {
    int score = new Random().nextInt(25);
    begin.await();
    
    try {
      Thread.sleep(score);
    } catch (Exception e) {
      e.printStackTrace();
    }
    end.countDown();
    return score;
  }

}
