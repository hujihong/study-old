package com.hujh.generic;

public class Producter {

  public static void main(String[] args) {
    Person person = new Person();
    person.setName("混世魔王");
    person.setAge(10);
    SerializationUtils.writeObject(person);
  }

}
