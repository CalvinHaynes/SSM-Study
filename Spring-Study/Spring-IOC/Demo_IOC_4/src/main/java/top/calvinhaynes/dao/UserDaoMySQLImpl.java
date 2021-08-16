package top.calvinhaynes.dao;

public class UserDaoMySQLImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("通过MySQL获取UserDAO中数据");
    }
}
