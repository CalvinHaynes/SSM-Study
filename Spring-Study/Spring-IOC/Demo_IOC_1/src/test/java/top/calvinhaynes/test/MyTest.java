package top.calvinhaynes.test;

import top.calvinhaynes.service.UserService;
import top.calvinhaynes.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.getUser();

    }
}
