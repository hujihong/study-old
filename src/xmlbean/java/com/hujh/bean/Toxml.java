package com.hujh.bean;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Toxml {

  public static void main(String[] args) {
    // toXml();
    // toBean();
    xmlTest();
    // xmlTest1();
  }
  
  public static void toBean() {
    String xml = "<person><firstname>Joe</firstname><lastname>Walnes</lastname><phone><code>123</code><number>1234-456</number></phone>"+
                "<fax><code>123</code><number>9999-999</number></fax></person>";
    XStream xstream = new XStream();
    Person newJoe = (Person)xstream.fromXML(xml); 
    System.out.println(newJoe);
  }
  
  public static void toXml() {
    XStream xstream = new XStream(); 
    // 如果不想使用xpp作为xml解析引擎，也可写为new XStream(new Dom4jDriver()); 设置映射规则： 
    xstream.alias("person", Person.class); 
    xstream.alias("phonenumber", PhoneNumber.class); 

    //将对象序列化为xml 
    Person joe = new Person("Joe", "Walnes"); 
    joe.setPhone(new PhoneNumber(123, "1234-456")); 
    joe.setFax(new PhoneNumber(123, "9999-999")); 

    String xml = xstream.toXML(joe); 
    System.out.println(xml);
  }
  
  
  public static void xmlTest() {
    XStream xstream = new XStream(); 
    xstream.alias("person", Person.class); 
    xstream.alias("phonenumber", PhoneNumber.class); 
    xstream.alias("personList", ArrayList.class); 
    // xstream.alias(name, type);
    // String xml = "<?xml version='1.0' encoding='GB2312'?><userlist><user><userid>admin@chanjet.com</userid><name>admin</name><departmentid>-1</departmentid><office></office><mobile></mobile><phone></phone><fax></fax></user><user><userid>hujh@chanjet.com</userid><name>胡继红</name><departmentid>2</departmentid><office>开发工程师</office><mobile>13811111111</mobile><phone></phone><fax></fax></user><user><userid>yangxl@chanjet.com</userid><name>杨献仑</name><departmentid>2</departmentid><office>开发工程师</office><mobile>13811111111</mobile><phone></phone><fax></fax></user></userlist>";
    String xml = "<personList><person><firstname>Joe</firstname><lastname>Walnes</lastname><phone><code>124</code><number>1234-450</number></phone><fax><code>124</code><number>9999-990</number></fax></person><person><firstname>one</firstname><lastname>one</lastname></person></personList>";
    Object o = xstream.fromXML(xml);
    List<Person> list = (List<Person>)o;
    for(Person p : list) {
      System.out.println(p);
    }
    // System.out.println(o);
  }
  
  
  public static void xmlTest1() {
    
    XStream xstream = new XStream(); 
    xstream.alias("person", Person.class); 
    xstream.alias("phonenumber", PhoneNumber.class); 
    xstream.alias("personList", ArrayList.class); 
    
    List<Person> personList = new ArrayList<Person>();
    Person joe = new Person("Joe", "Walnes"); 
    joe.setPhone(new PhoneNumber(123, "1234-456")); 
    joe.setFax(new PhoneNumber(123, "9999-999")); 
    personList.add(joe);
    
    Person one = new Person("one", "one"); 
    joe.setPhone(new PhoneNumber(124, "1234-450")); 
    joe.setFax(new PhoneNumber(124, "9999-990")); 
    personList.add(one);
    
    String xml = xstream.toXML(personList); 
    System.out.println(xml);
    
    List<Person> list = (List<Person>)xstream.fromXML(xml);
    System.out.println(list);
    
  }
   
}
