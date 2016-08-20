package com.study;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ByteBufferDemoTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testReadFile() throws Exception {
    ByteBufferDemo byteBufferDemo = new ByteBufferDemo();
    byteBufferDemo.readFile("/Users/hujh/git/study/src/nio/java/com/study/1.txt");

  }

}
