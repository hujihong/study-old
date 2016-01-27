package annotation.test;

import java.lang.reflect.Method;

public class KSFProcess {

  public static void processFilter(String clazz) throws SecurityException, ClassNotFoundException {

    int passed = 0;
    int failed = 0;

    // 遍历clazz对应的类里的所有方法
    for (Method m : Class.forName(clazz).getMethods()) {
      // 该方法使用了@Kangshifu修饰
      if (m.isAnnotationPresent(Kangshifu.class)) {
        try {
          // 调用m方法
          m.invoke(null);
          passed++;
        } catch (Exception e) {
          System.out.println("生产流程" + m + "运行异常：" + e.getCause());
          failed++;
        }
      }
    }

    // 统计测试结果
    System.out.println("该工厂共检测到生产流程" + (passed + failed) + "个，其中" + failed + "个失败，" + passed + "个正常!");
  }

  
  public static void main(String[] args) {
    try {
        KSFProcess.processFilter("annotation.BJFactory");

    } catch (SecurityException | ClassNotFoundException e) {
        // TODO 自动生成的 catch 块
        e.printStackTrace();
    }

}
  
}
