package com.github.im.java8;

import java.util.Arrays;
import java.util.List;

public class Lambda {

	public static void main(String[] args) {
		// thread1();
		thread8();
	}
	
	public static void thread1() {
		
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		    System.out.println("Before Java8, too much code for too little to do");
		    }
		}).start();
		
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
		    System.out.println(feature);
		}
		
	}
	
	public static void thread8() {
		// new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
		
		// Java 8之后：
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		// features.forEach(n -> System.out.println(n));
		 
		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		// features.forEach(System.out::println);
		
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		// costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
		System.out.println("--------------------");
		// costBeforeTax.forEach(System.out::println);
		double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
		
	}
}
