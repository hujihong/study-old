package com.hujh;

import org.junit.Test;

import junit.framework.Assert;

public class ProtoStuffSerializerUtilTest {
    
    public static class Person{
        int id;
        String name;
        
        public Person(){
            
        }
        
        public Person(int id, String name){
            this.id = id;
            this.name = name;
        }
        
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        
    }
    
    @Test
    public void demo(){
        Person p = new Person(1,"ff");
        byte[] arr = ProtoStuffSerializerUtil.serialize(p);
        Person result = ProtoStuffSerializerUtil.deserialize(arr, Person.class);
        Assert.assertEquals(p.getId(), result.getId());
        Assert.assertEquals(p.getName(), result.getName());
    }
}