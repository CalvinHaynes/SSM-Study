package top.calvinhaynes.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;


/**
 * 自定义注解测试
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
@MyAnnotation
public class MyTest02 {

    @MyAnnotation
    @Test
    public void test(){

    }

}

/**
 * 我的自定义注解
 *  - Target 表示注解用在哪些地方
 *  - Retention 表示我们的注解在什么地方还有效 ---> RUNTIME > CLASS > SOURCES
 *  - Documented 表示是否将我们的注解生成到Javadoc中
 *  - Inherited 子类可以继承父类的注解
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface MyAnnotation{

}
