package top.calvinhaynes.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: MyTest02
 * @Author: CalvinHaynes
 * @Date: 2021/8/10 0:15
 * @Description:自定义注解测试
 */
@MyAnnotation
public class MyTest02 {

    @MyAnnotation
    public void test(){

    }

}

//定义一个注解

//Target 表示注解用在哪些地方
@Target(value = {ElementType.METHOD, ElementType.TYPE})

//Retention 表示我们的注解在什么地方还有效
//RUNTIME > CLASS > SOURCES
@Retention(value = RetentionPolicy.RUNTIME)

//Documented 表示是否将我们的注解生成到Javadoc中
@Documented

//Inherited 子类可以继承父类的注解
@Inherited

@interface MyAnnotation{

}
