package top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo1_update.real_subject;

import top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo1_update.subject.Rent;

/**
 * @ProjectName: Host
 * @Author: CalvinHaynes
 * @Date: 2021/8/9 16:21
 * @Description:真实主题类：房东
 */
public class Host implements Rent {

    @Override
    public void rent() {
        System.out.println("房东：我有房子要出租");
    }
}
