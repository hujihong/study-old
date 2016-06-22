package annotation;

import java.util.HashSet;
import java.util.Set;

public class AppleTest {

  public static void main01(String[] args) {

    // FruitInfoUtil.getFruitInfo(Apple.class);

  }

  public static void main(String[] args) {
    /**
     * for (int i = 1; i < 100000; i++) { if (Math.sqrt(i + 100) % 1 == 0 &&
     * Math.sqrt(i + 268) % 1 == 0) { System.out.println(i); // break; } }
     **/
    // System.out.println(Math.sqrt(121));

    // System.out.println(sumDays(2015, 12, 31));
    // String s = null;
    // boolean b = s.equals("");
    // boolean b = "".equals(s);
    // System.out.println(b);
//    Integer key = 12;
//    c(key);
    
//    String s = new String("好事。".getBytes(), Charset.forName("UTF-8"));
//    System.out.println(s);
//    
//    String str = "好事。ddd";
//    String r = new String(str.getBytes(Charset.forName("UTF-8")), Charset.forName("UTF-8"));
//    System.out.println(r);
    
  }

  
  public static void setOpertion(String[] args) {
    Set<Integer> result = new HashSet<Integer>();
    Set<Integer> set1 = new HashSet<Integer>(){
      private static final long serialVersionUID = 1L;
      {
        add(1);
        add(3);
        add(5);
      }
      
    };
     
    Set<Integer> set2 = new HashSet<Integer>(){
      private static final long serialVersionUID = 1L;
      {
        add(1);
        add(2);
        add(3);
      }
     };
     
    result.clear();
    result.addAll(set1);
    result.retainAll(set2);
    System.out.println("交集："+result);
     
    result.clear();
    result.addAll(set1);
    result.removeAll(set2);
    System.out.println("差集："+result);
     
    result.clear();
    result.addAll(set1);
    result.addAll(set2);
    System.out.println("并集："+result);
     
}
  
  public static void c(Object key) {
    if(key instanceof Long || key instanceof Integer || key instanceof Short){
      System.out.println("true");
    }else{
      System.out.println("false");
    }
  }
  
  
  public static boolean isLeapYear(int y) {
    if ((y % 4 == 0 && y % 100 != 100) || y % 400 == 0)
      return true;
    else
      return false;
  }

  public static int sumDays(int y, int m, int d) {
    int[] MonthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    if (isLeapYear(y)) MonthDays[1] = 29;
    int ans = 0;
    for (int i = 0; i < m - 1; i++) {
      ans = ans + MonthDays[i];
    }
    return ans + d;
  }

}
