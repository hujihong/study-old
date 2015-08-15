package com.github.im.common.config;

/**
 * 环境定义
 */
public enum Env {
	TEST("/test", 1), UNI("/uni", 2), INTE("/inte", 3), MONI("/moni", 4), PROD("/prod", 5), SHAHE("/shahe", 6);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private Env(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (Env c : Env.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static void main(String a[]){
		System.out.println(Env.TEST.getName());
	}
}