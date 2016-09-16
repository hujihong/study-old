package com.hujh.ex;

public class TcpServer implements Runnable {

  public TcpServer() {
    Thread thread = new Thread(this);
    thread.setUncaughtExceptionHandler(new TcpServerExceptionHandler());;
    thread.start();
  }
  
  @Override
  public void run() {
    for(int i = 0; i<3; i++) {
      try {
        Thread.sleep(1000);
        System.out.println("系统正常 " + i);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    throw new RuntimeException();
  }
  
  
  private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
      System.out.println("thread " + t.getName() + "异常");
      e.printStackTrace();
      // 重启线程
      new TcpServer();
    }
    
  }
}
