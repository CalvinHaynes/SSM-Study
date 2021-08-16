package top.calvinhaynes.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import top.calvinhaynes.dao.UserDao;
import top.calvinhaynes.dao.UserDaoImpl;
import top.calvinhaynes.dao.UserDaoMySQLImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoMySQLImpl();

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
