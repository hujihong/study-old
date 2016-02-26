package annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Classes1 {

  private static class InstanceHolder {
    private static final Classes1 instance = new Classes1();
  }

  public static Classes1 getInstance() {
    return InstanceHolder.instance;
  }
  
  private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

  public void setMap() {
    map.put("a", "a");
    map.put("b", "b");
  }

  public ConcurrentHashMap<String, String> getMap() {
    return map;
  }

  public void bu() {
    List<String> list = new ArrayList<String>();
    list.add("a");
    list.add("b");
    list.add("c");
    for (String s : list) {
      if (map.containsKey(s)) {
        System.out.println(true);
      } else {
        System.out.println(false);
      }
    }

  }

  public void setMap(ConcurrentHashMap<String, String> map) {
    this.map = map;
  }

  
  
}
