package top.calvinhaynes.demo1_rent.proxy;

import top.calvinhaynes.demo1_rent.real_subject.Host;
import top.calvinhaynes.demo1_rent.subject.Rent;

public class Proxy implements Rent {
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    //中介业务流程
    @Override
    public void rent() {
        host.rent();
        seeHouse();
        fare();
    }

    //看房业务
    public void seeHouse() {
        System.out.println("中介：带客户看房");
    }

    //收中介费业务
    public void fare() {
        System.out.println("中介：业务结束，收中介费");
    }
}
