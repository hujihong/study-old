package com.hujh.generic;

import java.io.FileReader;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptMain {

  public static void main(String[] args)  {
    try {
      ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
      Bindings bindings = engine.createBindings();
      bindings.put("factor", 1);
      engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
      Scanner input = new Scanner(System.in);
     while (input.hasNextInt()) {
       int first = input.nextInt();
       int sec = input.nextInt();
       System.out.println("输入参数是：" + first + "," + sec);
       engine.eval(new FileReader("/Users/hujh/git/study/src/other/java/com/hujh/generic/mode.js"));
       if(engine instanceof Invocable) {
         Invocable invocable = (Invocable)engine;
         Double result = (Double) invocable.invokeFunction("formula", first, sec);
         System.out.println("结果：" + result.intValue());
       }
     }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
