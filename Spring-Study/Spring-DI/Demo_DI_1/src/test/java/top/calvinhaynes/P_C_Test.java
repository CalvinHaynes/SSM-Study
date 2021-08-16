package top.calvinhaynes;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.pojo.Person;

public class P_C_Test {

    //p命名空间注入测试
    @Test
    public void test_p(){
        ApplicationContext context = new ClassPathXmlApplicationContext("person.xml");

        Person person_p = context.getBean("person_p", Person.class);
        System.out.println(person_p.toString());
    }

    //c命名空间测试
    @Test
    public void test_c(){
        ApplicationContext context = new ClassPathXmlApplicationContext("person.xml");

        Person person_c = context.getBean("person_c", Person.class);
        System.out.println(person_c.toString());
    }
}
