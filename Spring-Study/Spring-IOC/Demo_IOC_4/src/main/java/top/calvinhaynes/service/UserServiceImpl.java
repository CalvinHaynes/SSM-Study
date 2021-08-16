package top.calvinhaynes.service;

import top.calvinhaynes.dao.UserDao;
import top.calvinhaynes.dao.UserDaoMySQLImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoMySQLImpl();

    //利用set实现IOC思想
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
