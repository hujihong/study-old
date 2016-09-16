package com.hujh.enumtest;

public enum Season {
  Spring("春"), Summer("夏"), Autumn("秋"), Winter("冬");
  private String desc;
  
  Season(String _desc) {
    desc = _desc;
  }
  
  public String getDesc() {
    return desc;
  }
  
  public static Season getComfortableSeason() {
    return Season.Spring;
  }
  
  
  
}
