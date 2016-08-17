package com.soft;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WW {
  public <T> void write(T t, T[] ta) {

  }

  public <S> void read(S s, S[] sa) {

  }

  public <T, S extends T> T test(T t, S s) {
    S ss = null;
    return ss;
  }

  public <T> void test1(List<T> u) {}
  public <S> void test2(List<S> u) {}
  
  // 通配符方式
  public void test(List<?> s){}
  

 
  
  
}