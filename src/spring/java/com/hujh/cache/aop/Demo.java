package com.hujh.cache.aop;

import java.io.Serializable;
import java.util.Date;

public class Demo implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 2461340078713008166L;
  private long id;
  private String name;
  private Date createTime;
  
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  
  
}
