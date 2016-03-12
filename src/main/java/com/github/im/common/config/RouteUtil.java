package com.github.im.common.config;

import java.util.HashMap;
import java.util.Map;

// 分表定义
public class RouteUtil {
	private static final RouteUtil instance = new RouteUtil();

	private RouteUtil() {
	}

	public synchronized static RouteUtil getInstance() {
		return instance;
	}

	private static final Map<Integer, String> TEST_TABLE_MAP = new HashMap<Integer, String>();
	
	static {
		TEST_TABLE_MAP.put(0, "test");
	}

	public String getTestTableName(long key) {
		long i = key % TEST_TABLE_MAP.size();
		return TEST_TABLE_MAP.get((int) i);
	}
	
	public String getTestTableName(String key) {
	  int temp = key.hashCode();
	  int i = 0;
	  if(temp != Integer.MIN_VALUE){
	    i = Math.abs(temp) % TEST_TABLE_MAP.size();
	  }else{
	    i = Integer.MIN_VALUE % TEST_TABLE_MAP.size();  
	  }
		// long i = Math.abs((key.hashCode())) % TEST_TABLE_MAP.size();
		return TEST_TABLE_MAP.get((int) i);
	}
	
	
}
