package top.calvinhaynes.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        UserServiceImpl userService = (UserServiceImpl) context.getBean("test1");
        userService.getUser();

    }
}
