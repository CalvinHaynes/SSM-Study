package top.calvinhaynes.service;

import top.calvinhaynes.dao.UserDao;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl() {
        System.out.println("UserServiceImpl的无参构造器调用。。。");
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;

        System.out.println("UserServiceImpl的有参构造器调用。。。");
    }

    //利用set实现IOC思想
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
