package com.hujh.cache.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CacheAOPTest {

  private DemoServiceImpl demoService;

  // private final Logger logger = LoggerFactory.getLogger(CacheAOPTest.class);

  @Before
  public void setUp() throws Exception {
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cacheaop.xml");
      demoService = context.getBean("demoService", DemoServiceImpl.class);
  }
  
  @Test
  public void test1get() {
    Demo demo = demoService.get(1);
    System.out.println(demo);
    demo = demoService.get(1);
    System.out.println(demo);
    demo = demoService.get(1);
    System.out.println(demo);
  }
}
