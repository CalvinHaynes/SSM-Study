package top.calvinhaynes.demo2_user.proxy_test;

import top.calvinhaynes.demo1_rent.proxy.Proxy;
import top.calvinhaynes.demo1_rent.real_subject.Host;
import top.calvinhaynes.demo2_user.proxy.UserServiceProxy;
import top.calvinhaynes.demo2_user.real_subject.UserServiceImpl;

/**
 * @ProjectName: Client
 * @Author: CalvinHaynes
 * @Date: 2021/8/9 16:26
 * @Description:客户测试类
 */
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);

        proxy.addUser();
        proxy.deleteUser();
        proxy.updateUser();
        proxy.queryUser();
    }
}
