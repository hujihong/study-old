package com.hujh.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;



public class CollectTest {

  public static void main(String[] args) {
    listvector();
  }
  
  public static void sub() {
    List<Integer> initData = Collections.nCopies(100, 0);
    ArrayList<Integer> list = new ArrayList<>(initData);
    System.out.println(list);
    List<Integer> sub = list.subList(20, 30);
    System.out.println("list == sub ?" + list.equals(sub));
    System.out.println(sub);
    sub.clear();
    System.out.println(list);
  }
  
  public static void listvector() {
    ArrayList<String> strings = new ArrayList<>();
    strings.add("A");
    
    Vector<String> strings2 = new Vector<>();
    strings2.add("A");
    System.out.println(strings.equals(strings2));
    
    HashSet<String> set = new HashSet<>();
    set.add("A");
    TreeSet<String> treeSet = new TreeSet<>();
    treeSet.add("A");
    System.out.println(set.equals(treeSet));
  }
}
