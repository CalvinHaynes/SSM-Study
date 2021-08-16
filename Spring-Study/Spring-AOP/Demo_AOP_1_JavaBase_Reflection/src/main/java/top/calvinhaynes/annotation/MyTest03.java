package top.calvinhaynes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@MyAnnotation2(age=18)
public class MyTest03 {

    @MyAnnotation3("Calvin Haynes")
    public void test3(){

    }


}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //注解参数设置格式：
    //参数类型 参数名 默认值(可选)
    String name() default "";
    int age();
    int id() default -1;    //默认值 -1 代表不存在

    String[] schools() default {"清华大学","北京大学"};
}


//当注解只有一个参数时,最好名字是 value
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}
