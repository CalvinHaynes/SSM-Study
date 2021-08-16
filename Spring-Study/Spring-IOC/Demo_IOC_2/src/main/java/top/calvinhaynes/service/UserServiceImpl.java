package top.calvinhaynes.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import top.calvinhaynes.dao.UserDao;
import top.calvinhaynes.dao.UserDaoImpl;
import top.calvinhaynes.dao.UserDaoMySQLImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    //利用set实现IOC思想
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
