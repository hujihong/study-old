package com.hujh.generic;

public class Consumer {

  public static void main(String[] args) {
    Person person = (Person)SerializationUtils.readObject();
    System.out.println(person);
    System.out.println(person.aString);
    System.out.println(person.bString);
  }

}
