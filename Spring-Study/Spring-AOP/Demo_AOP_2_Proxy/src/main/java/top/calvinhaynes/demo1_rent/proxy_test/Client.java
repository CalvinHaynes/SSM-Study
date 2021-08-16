package top.calvinhaynes.demo1_rent.proxy_test;

import top.calvinhaynes.demo1_rent.proxy.Proxy;
import top.calvinhaynes.demo1_rent.real_subject.Host;

/**
 * @ProjectName: Client
 * @Author: CalvinHaynes
 * @Date: 2021/8/9 16:26
 * @Description:买房的客户
 */
public class Client {
    public static void main(String[] args) {
        Host host = new Host(); //房东

        Proxy proxy = new Proxy(host);  //中介和房东谈成合作
        proxy.rent();   //中介和客户进行业务流程执行
    }
}
