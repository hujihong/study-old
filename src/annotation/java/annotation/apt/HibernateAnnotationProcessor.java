package annotation.apt;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

/**
 * javac -processor HibernateAnnotationProcessor Person.java
使用上一句的前提是HibernateAnnotationProcessor已经编译，即当前目录下存在HibernateAnnotationProcessor.class文件。
 *
 */

@SupportedSourceVersion(SourceVersion.RELEASE_7)
// 指定可处理@Persistent、@Id、@Property三个Annotation
@SupportedAnnotationTypes({ "Persistent", "Id", "Property" })
public class HibernateAnnotationProcessor extends AbstractProcessor {

  // 循环处理每个需要处理的程序对象
  @Override
  // public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
  public boolean process(Set<? extends TypeElement>annotations, RoundEnvironment roundEnv) {
     //定义一个文件输出流，用于生成额外的文件
     PrintStream ps = null;
     try {
         for (Element t :roundEnv.getElementsAnnotatedWith(Persistent.class)){
            //获取正在处理的类名
            Name clazzName = t.getSimpleName();
            //获取类定以前的@Persistent Annotation
            Persistent per = t.getAnnotation(Persistent.class);
            ps= new PrintStream(new FileOutputStream(clazzName + ".hbm.xml"));
            ps.println("<?xml version=\"1.0\"?>");
            ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
            ps.println(" \"-//Hibernate/HibernateMapping DTD 3.0//EN\"");
            ps.println("   \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">");
            ps.println("<hibernate-mapping>");
            ps.print("<class name=\"" + t);
            //输出per的table()的值
            ps.println("\" table=\""+ per.table() + "\">");
            for (Element f :t.getEnclosedElements()) {
               //只处理Field上的Annotation
               if(f.getKind() ==ElementKind.FIELD){
                   //获取Field定义前的@Id Annotation
                  Id id = f.getAnnotation(Id.class);
                  //当@Id Annotation存在时，输出<id.../>元素
                  if(id != null){
                     ps.println("    <idname=\"" + f.getSimpleName()
                            +"\" column=\"" + id.column()
                            +"\" type=\"" + id.type() + "\">");
                     ps.println("    <generatorclass=\"" + id.generator() + "\"/>");
                     ps.println("    </id>");
                  }
                  //获取Field定义前的@Property Annotation
                  Property p = f.getAnnotation(Property.class);
                  if(p != null){
                     ps.println("    <propertyname=\"" + f.getSimpleName()
                            +"\" column=\"" + p.column()
                            +"\" type=\"" + p.type() + "\" />");
                  }
               }
            }
            ps.println(" </class>");
            ps.println("</hibernate-mapping>");
         }
     }catch(Exception e) {
         e.printStackTrace();
     }finally{
         if(ps != null){
            try {
               ps.close();
            }catch(Exception e2) {
               e2.printStackTrace();
            }
         }
     }
     return true;
}
  
 
  
}
