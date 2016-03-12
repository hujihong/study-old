package com.hujh.xmljson;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;  
  
public class Test {  
    public static void convertXMLtoJSON()  {  
        InputStream is = Test.class.getResourceAsStream("/sample.xml");  
       
        String xml;  
        try {  
            xml = IOUtils.toString(is);  
            System.out.println(xml);  
            XMLSerializer xmlSerializer = new XMLSerializer();  
            JSON json = xmlSerializer.read(xml); 
            // System.out.println(json.toString(1));  
            JSONArray jsonArray = JSONArray.fromObject(json.toString());
            
            for(Object o : jsonArray) {
              JSONObject j = (JSONObject)o;
              Object of = j.get("office");
              System.out.println(of);
//              if(of.equals("[]")) {
//                System.out.println("office:[]");
//              }
              
              System.out.println(j);
            }
            
            // System.out.println(jsonArray);
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) {  
        convertXMLtoJSON();  
    }  
    
    /**
     * 将xml字符串转换为JSON对象
     * @param xmlFile xml字符串
     * @return JSON对象
     */
    public JSON getJSONFromXml(String xmlString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xmlString);
        return json;
    }

    /**
     * 将xmlDocument转换为JSON对象
     * @param xmlDocument XML Document
     * @return JSON对象
     */
    public JSON getJSONFromXml(Document xmlDocument) {
        String xmlString = xmlDocument.toString();
        return getJSONFromXml(xmlString);
    }

    /** 
     * 将xml字符串转换为JSON字符串 
     * @param xmlString 
     * @return JSON字符串 
     */  
    public String getJSONStringFromXml(String xmlString ) {  
        return getJSONFromXml(xmlString).toString();  
    } 
    
    /** 
     * 将xmlDocument转换为JSON字符串 
     * @param xmlDocument XML Document 
     * @return JSON字符串 
     */  
    public String getXMLtoJSONString(Document xmlDocument) {  
        return getJSONStringFromXml(xmlDocument.toString());  
    }  
    
    /** 
     * 读取XML文件准换为JSON字符串 
     * @param xmlFile  XML文件 
     * @return JSON字符串 
     */  
    public String getXMLFiletoJSONString(String xmlFile) {  
        InputStream is = Test.class.getResourceAsStream(xmlFile);  
        String xml;  
        JSON json = null;  
        try {  
            xml = IOUtils.toString(is);  
            XMLSerializer xmlSerializer = new XMLSerializer();  
            json = xmlSerializer.read(xml);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return json.toString();  
    }  
    
    /** 
     * 将Java对象转换为JSON格式的字符串 
     *  
     * @param javaObj 
     *            POJO,例如日志的model 
     * @return JSON格式的String字符串 
     */  
    public static String getJsonStringFromJavaPOJO(Object javaObj) {  
        return JSONObject.fromObject(javaObj).toString(1);  
    }  
    
    /** 
     * 将Map准换为JSON字符串 
     * @param map 
     * @return JSON字符串 
     */  
    public static  String getJsonStringFromMap(Map<?, ?> map) {  
        JSONObject object = JSONObject.fromObject(map);  
        return object.toString();  
    }
    
    

}  