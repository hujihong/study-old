package com.example.tutorial;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ByteString;
import com.google.protobuf.TextFormat;

public class MessageTest {

  
  public static void main(String[] args) {
    MessageProto.SearchRequest.Builder request = MessageProto.SearchRequest.newBuilder();
    request.setQuery("query没");
    request.setPageNumber(23);
    request.setResultPerPage(20);
    ByteString bString = ByteString.copyFromUtf8("哈hu");
    request.setBins(bString);
    request.addList("A");
    request.addList("B");
    List<String> list = new ArrayList<>();
    list.add("C");
    list.add("D");
    request.addAllList(list);
    
    MessageProto.SearchRequest re = request.build();
    System.out.println(re.getPageNumber());
    System.out.println(re.getQuery());
    System.out.println(re.getResultPerPage());
    System.out.println(re.getBins().toStringUtf8());
    System.out.println("");
    System.out.println(TextFormat.shortDebugString(request));
    
    // System.out.println(TextFormat.sh);
  }
  
  
}
