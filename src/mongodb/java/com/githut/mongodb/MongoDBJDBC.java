package com.githut.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class MongoDBJDBC {

  public static void main(String args[]) {
    String username = "xlybase";
    String pass = "xlybase";
    try {
      /**
      // 连接到 mongodb 服务
      MongoClient mongoClient = new MongoClient("123.57.58.144", 27317);
      // 连接到数据库
      DB db = mongoClient.getDB("test_2");
      System.out.println("Connect to database successfully");
      boolean auth = db.authenticate(username, pass.toCharArray());
      System.out.println("Authentication: " + auth);
      DBCollection coll = db.createCollection("mycol");
      System.out.println("Collection created successfully");
      DBCollection coll = db.getCollection("mycol");
      System.out.println("Collection mycol selected successfully");
      **/
      
      MongoCredential cr = MongoCredential.createCredential(username, "xlybase", pass.toCharArray());
      List<MongoCredential> list_mc = new ArrayList<MongoCredential>();
      list_mc.add(cr);
      
      MongoClient mongoClient = null;
      ServerAddress serverAddress = new ServerAddress("123.57.58.144", 27317);
      // 其他参数根据实际情况进行添加
      MongoClientOptions mco = new MongoClientOptions.Builder()
          // .autoConnectRetry(autoConnectRetry)
          .writeConcern(WriteConcern.SAFE).connectionsPerHost(10).threadsAllowedToBlockForConnectionMultiplier(30)
          .connectTimeout(3000).socketTimeout(3000).maxWaitTime(3000).build();
      mongoClient = new MongoClient(serverAddress, list_mc, mco);
      DB db = mongoClient.getDB("xlybase");
     
//      BasicDBObject document = new BasicDBObject();
//      document.append("id", "id3");
//      document.append("name", "name3");
//      
//      WriteResult result = db.getCollection("test_2").save(document);
//      System.out.println("result:" + result);

      long count = db.getCollection("test_2").count();
      System.out.println(count);
     
      DBCursor cursor = db.getCollection("test_2").find();
      while(cursor.hasNext()){
        DBObject dbObject = cursor.next();
        System.out.print(dbObject.get("id") + "   ");
        System.out.print(dbObject.get("name") + "   ");
        System.out.println();
      }
      
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}