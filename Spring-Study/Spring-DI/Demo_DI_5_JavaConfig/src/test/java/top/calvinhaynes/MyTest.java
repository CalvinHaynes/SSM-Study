package top.calvinhaynes;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.config.MyConfig;
import top.calvinhaynes.config.MyConfig2;
import top.calvinhaynes.pojo.Dog;
import top.calvinhaynes.pojo.Person;

public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig2.class);

        Person person = context.getBean("person", Person.class);
        System.out.println(person.name);
        System.out.println(person.dog.getName());

        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(dog.name);

    }


}
