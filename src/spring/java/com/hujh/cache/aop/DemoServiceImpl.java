package com.hujh.cache.aop;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl {  
  

    public List<Demo> findAll() {  
        System.out.println("excute findAll");
        return null;
    }  
      
    /* 
        对get()方法配置使用缓存,缓存有效期为3600秒,缓存的key格式为:{package_name}.DemoServiceImpl.get 
        同时为参数配置了@CacheKey后,表示此参数的值将做为key的后缀,此例的key,最终是:{package_name}.DemoServiceImpl.get.{id} 
        可以为多个参数配置@CacheKey,拦截器会调用参数的toString()做为key的后缀 
        若配置多个@CacheKey参数,那么最终的key格式为:{package_name}.{class_name}.{method}.{arg1}.{arg2}.{...} 
     */  
    @Cacheable(expire=3600)  
    public Demo get(@CacheKey long id) {
        System.out.println("excute get");
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("name");
        demo.setCreateTime(new Date());
        return demo;
    }  
  
    public Demo getByName(String name) {  
        return null;
    }  
} 