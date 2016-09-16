package com.hujh.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainTest {

  
  public static void main(String[] args) throws Exception {
    test1();

  }

  public static void test1() throws Exception {
    int num = 10;
    CountDownLatch begin = new CountDownLatch(1);
    CountDownLatch end = new CountDownLatch(num);
    ExecutorService es = Executors.newFixedThreadPool(num);
    List<Future<Integer>> futures = new ArrayList<>();
    for(int i = 0; i < num; i++) {
      futures.add(es.submit(new Runner(begin, end)));
    }
    // 
    begin.countDown();
    end.await();
    int count = 0;
    for(Future<Integer> f : futures) {
      count += f.get();
    }
    System.out.println("平均分数为：" + count / num);
    
  }
}
