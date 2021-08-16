package top.calvinhaynes.demo3_dynamic_proxy_by_jdk.demo2_update.proxy_invocation_handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//用这个类自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;

    //给外部的设置代理接口的方法
    public void setTarget(Object target) {
        this.target = target;
    }

    //动态代理的核心方法,靠此方法动态生成代理类
    public Object getProxy() {
        /*
         * Method Description:
         *      Proxy.newProxyInstance：静态方法
         *          通过指定的类加载器、需要实现的接口列表、指定的调用处理程序创建一个代理实例
         *
         * @param: [loader,interfaces,h]
         *          1.loader – 定义代理类的类加载器
         *          2.interfaces - 代理类要实现的接口列表
         *          3.h – 实现 InvocationHandler 的实现类，
         *                  调用处理程序写在重写的 invoke 方法中
         *
         * @return: java.lang.Object
         * @author: CalvinHaynes
         * @date: 2021/8/15 0:02
         */
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    // 处理代理实例上的方法调用并返回结果
    //具有代理类的指定调用处理程序的代理实例，该代理类由指定的类加载器定义并实现指定的接口
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //核心：本质利用反射实现！
        log(method.getName());
        Object result = method.invoke(target, args);
        return result;
    }

    //如果增加一个方法
    public void log(String msg){
        System.out.println("执行了" + msg + "方法");
    }
}