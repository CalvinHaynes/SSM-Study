package top.calvinhaynes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 我test03
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
@MyAnnotation2(age=18)
public class MyTest03 {

    @MyAnnotation3("Calvin Haynes")
    public void test3(){

    }


}

/**
 * 我的自定义注解
 *  - 注解参数设置格式：参数类型 参数名() 默认值(可选)
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{

    String name() default "";
    int age();
    //默认值 -1 代表不存在
    int id() default -1;
    String[] schools() default {"清华大学","北京大学"};
}


/**
 * 我的自定义注解
 *  - 当注解只有一个参数时,最好名字是 value
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}
