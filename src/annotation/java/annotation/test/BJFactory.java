package annotation.test;

public class BJFactory {
  
  // 用来标记该方法用于生产康师傅产品
  @Kangshifu
  public static void creatType1() {}

  public static void creatType2() {}
  
  @Kangshifu
  public static void creatType3() {}

  @Kangshifu
  public static void creatType4() {
    throw new NullPointerException("原料不足");
  }

  public static void creatType5() {}
  
}
