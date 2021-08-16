package top.calvinhaynes;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.pojo.User;

public class MyTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        User user = context.getBean("user", User.class);
        User user1 = context.getBean("user", User.class);

        System.out.println(user == user1);


    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        User user = context.getBean("user1", User.class);
        User user1 = context.getBean("user1", User.class);

        System.out.println(user == user1);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        User user = context.getBean("user0", User.class);
        User user1 = context.getBean("user0", User.class);

        System.out.println(user == user1);
    }
}
