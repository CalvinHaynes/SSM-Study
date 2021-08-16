package top.calvinhaynes;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.service.UserService;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //动态代理代理的是接口
        UserService userService = context.getBean("userService", UserService.class);

        userService.addUser();
    }
}
