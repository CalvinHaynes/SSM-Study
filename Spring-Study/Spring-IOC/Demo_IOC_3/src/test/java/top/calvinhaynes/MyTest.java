package top.calvinhaynes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        //获取bean
        Object hello = applicationContext.getBean("hello");
        //调用toString方法测试一下
        System.out.println(hello.toString());
    }
}
