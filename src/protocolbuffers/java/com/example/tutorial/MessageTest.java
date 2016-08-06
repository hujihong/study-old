package com.example.tutorial;

import com.google.protobuf.ByteString;

public class MessageTest {

  
  public static void main(String[] args) {
    MessageProto.SearchRequest.Builder request = MessageProto.SearchRequest.newBuilder();
    request.setQuery("query");
    request.setPageNumber(23);
    request.setResultPerPage(20);
    ByteString bString = ByteString.copyFromUtf8("哈hu");
    request.setBins(bString);
    
    MessageProto.SearchRequest re = request.build();
    System.out.println(re.getPageNumber());
    System.out.println(re.getQuery());
    System.out.println(re.getResultPerPage());
    System.out.println(re.getBins().toStringUtf8());
  }
  
  
}
