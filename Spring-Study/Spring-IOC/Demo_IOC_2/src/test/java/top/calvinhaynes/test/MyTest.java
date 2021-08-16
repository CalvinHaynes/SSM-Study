package top.calvinhaynes.test;

import top.calvinhaynes.dao.UserDaoMySQLImpl;
import top.calvinhaynes.dao.UserDaoOracleImpl;
import top.calvinhaynes.service.UserService;
import top.calvinhaynes.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.setUserDao(new UserDaoMySQLImpl());
        userService.getUser();

        userService.setUserDao(new UserDaoOracleImpl());
        userService.getUser();
    }
}
