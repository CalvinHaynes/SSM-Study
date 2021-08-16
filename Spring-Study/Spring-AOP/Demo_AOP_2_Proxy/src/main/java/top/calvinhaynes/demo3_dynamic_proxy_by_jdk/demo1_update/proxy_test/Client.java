package top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo1_update.proxy_test;

import top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo1_update.proxy_invocation_handler.ProxyInvocationHandler;
import top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo1_update.real_subject.Host;
import top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo1_update.subject.Rent;


/**
 * @ProjectName: Client
 * @Author: CalvinHaynes
 * @Date: 2021/8/9 16:26
 * @Description:买房的客户
 */
public class Client {
    public static void main(String[] args) {
        //真实角色
        Rent host = new Host();

        //创建一个代理角色生成器，现在还没产生代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();

        //设置代理角色要代理的接口,就是传入真实角色
        pih.setRent(host);

        //动态生成代理类
        Rent pihProxy = (Rent) pih.getProxy();

        //调用方法测试
        pihProxy.rent();
    }
}
