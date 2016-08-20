package com.study;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ByteBufferDemo {

  // 读文件 
  public void readFile( String filename) throws Exception {
    RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw");
    FileChannel fileChannel = randomAccessFile.getChannel();
    ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
    // CharBuffer buf = CharBuffer.allocate(1024);
    int size = fileChannel.read(byteBuffer);
    while(size != -1) {
      byteBuffer.flip();
//      while (byteBuffer.hasRemaining()) {
//        System.out.print(byteBuffer.getChar());
//        // System.out.print((char)byteBuffer.get());
//      }
      // 上面会产生乱码
      Charset charset = Charset.forName("UTF-8");
      System.out.println(charset.newDecoder().decode(byteBuffer).toString());
      
      // byteBuffer.clear();
      byteBuffer.compact();
      size = fileChannel.read(byteBuffer);
    }
    fileChannel.close();
    randomAccessFile.close();
    
    
  }
  
  
  
}
