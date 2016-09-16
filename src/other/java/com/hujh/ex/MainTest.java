package com.hujh.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jodd.datetime.TimeUtil;

public class MainTest {

  public static void main(String[] args) throws Exception {
    // test1();
    // test2();
    test3();
  }

  public static void test3() throws Exception {
    ExecutorService es = Executors.newSingleThreadExecutor();
    Future<Integer> future = es.submit(new TaxCalculator(100));
    while(!future.isDone()) {
      try {
        Thread.sleep(500);
        System.out.print("#");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println("计算完成：" + future.get());
   
    es.shutdown();
  }
  
  public static void test2() throws Exception{
    int value = 1000;
    int loops = 0;
    ThreadGroup tg = Thread.currentThread().getThreadGroup();
    while(loops ++ < value) {
      System.out.println("loops=" + loops);
      UnsafeThread ut = new UnsafeThread();
      for (int i = 0; i < value; i++) {
        new Thread(ut).start();
      }
      do {
        Thread.sleep(15);
      }while(tg.activeCount() != 1);
      
      if(ut.getCount() != value) {
        // 出现线程不安全情况
        System.out.println(" 循环到 " + loops + "出错异常");
        System.out.println(" 此时， count=" + ut.getCount());
        System.exit(0);
      }
    }
  }
  
  public static void test1() {
    TcpServer tcpServer = new TcpServer();
    Thread thread = new Thread(tcpServer);
    thread.start();
    try {
      Thread.sleep(10000000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
