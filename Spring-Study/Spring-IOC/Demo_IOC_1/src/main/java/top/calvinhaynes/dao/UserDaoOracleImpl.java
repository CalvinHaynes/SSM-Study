package top.calvinhaynes.dao;

public class UserDaoOracleImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("通过Oracle获取UserDao中数据");
    }
}
