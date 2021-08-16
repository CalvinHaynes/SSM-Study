package top.calvinhaynes;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.pojo.Person;

public class MyTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person person = context.getBean("person3", Person.class);
        System.out.println(person);
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person person = context.getBean("person", Person.class);
        person.getCat().shout();
        person.getDog().shout();
        System.out.println(person);
    }
}
