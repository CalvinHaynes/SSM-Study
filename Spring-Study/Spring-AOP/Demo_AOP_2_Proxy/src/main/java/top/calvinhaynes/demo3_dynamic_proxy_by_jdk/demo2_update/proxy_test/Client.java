package top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo2_update.proxy_test;


import top.calvinhaynes.demo2_user.real_subject.UserServiceImpl;
import top.calvinhaynes.demo2_user.subject.UserService;
import top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo2_update.proxy_invocation_handler.ProxyInvocationHandler;

public class Client {
    public static void main(String[] args) {

        //真实角色
        UserService userService = new UserServiceImpl();

        //代理角色,此时不存在
        ProxyInvocationHandler pih = new ProxyInvocationHandler();

        //设置代理接口
        pih.setTarget(userService);

        //动态生成代理对象
        UserService pihProxy = (UserService) pih.getProxy();

        //测试
        pihProxy.addUser();
        pihProxy.deleteUser();
        pihProxy.updateUser();
        pihProxy.queryUser();
    }
}
