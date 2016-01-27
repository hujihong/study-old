package annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//使该Annotation在程序运行时起作用
@Retention(RetentionPolicy.RUNTIME)
//该Annotation只能修饰方法
@Target(ElementType.METHOD)
public @interface Kangshifu {

}
