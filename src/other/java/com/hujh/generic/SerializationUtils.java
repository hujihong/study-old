package com.hujh.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationUtils {
  private static String FILE_NAME="/Users/hujh/Desktop/tmp/tmp/1.dat";
  public static void writeObject(Serializable s) {
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
      objectOutputStream.writeObject(s);
      objectOutputStream.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static Object readObject() {
    Object object = null;
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
      object = objectInputStream.readObject();
      objectInputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return object;
  }
}
