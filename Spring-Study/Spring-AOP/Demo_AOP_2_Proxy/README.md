# 代理模式
## 前言
代理模式的基本代码实现，代理模式这种设计模式是Spring AOP 的底层实现，包含动态代理和静态代理。

**什么是代理模式？**
- 代理模式就是相当于多了一个中间商，过去需要客户直接与商家联系办理业务，现在经过中间商办理业务，客户和商家只是纯粹的提供资源就行了
- 在软件设计中，使用代理模式的例子也很多，例如，要访问的远程对象比较大（如视频或大图像等），其下载要花很多时间。还有因为安全原因需要屏蔽客户端直接访问真实对象，如某单位的内部数据库等。

## 1 - 静态代理

### 1 - 静态代理模式的结构
代理模式中的几个角色如下：
- 抽象主题类（Subject）：通过接口或者抽象类说明真实主题和代理对象的业务方法
- 真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
- 代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
- 客户（ProxyTest）：中介帮助客户执行中介业务
  代理模式的结构图：
  ![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.34ncrg3qzie0.png)

### 2 - 静态代理的优缺点
代理模式的主要优点有：
- 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；
- 代理对象可以扩展目标对象的功能；
- 代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度，增加了程序的可扩展性

其主要缺点是：
- 代理模式会造成系统设计中类的数量增加
- 在客户端和目标对象之间增加一个代理对象，会造成请求处理速度变慢；
- 增加了系统的复杂度；

## 2 - 动态代理

### 1 - 动态代理的核心思想/特点
- 动态代理和静态代理的角色一样

- 动态代理的代理类是动态生成的，没有真实的代理类

- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理

  - 基于接口 ---- JDK 动态代理
  - 基于类 ---- GGLib
  - Java 字节码实现 ---- JAVAssist

  > ==接口代理，有以下特点==： 

  > - 代理对象不需要实现接口
  > - 代理对象的生成是利用 JDK 的 API 动态的在内存中构建代理对象
  > - 能在代码运行时动态地改变某个对象的代理，并且能为代理对象动态地增加方法、

- 一般情况下，动态代理的底层不用我们亲自去实现，可以使用线程提供的 API 。例如，在 Java 生态中，目前普遍使用的是 JDK 自带的代理和 GGLib（Spring-AOP） 提供的类库。

### 2 - 动态代理的优点/和静态代理比较
- 静态代理只能通过手动完成代理操作，如果被代理类增加了新的方法，则代理类需要同步增加，不够灵活，也使得代码量大大增加
- 动态代理采用在运行时动态生成代码的方式，取消了对被代理类的扩展限制，若动态代理要对目标类的增强逻辑进行扩展，结合策略模式，只需要新增策略类便可完成，无需修改代理类的代码。
- 一个动态代理 , 一般代理某一类业务
- 一个动态代理可以代理多个类，但其实代理的是接口！（Java的单继承特性导致接口的出现）

### 3 - JDK 动态代理处理类的通用写法（公式）

```java
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
        Object result = method.invoke(target, args);
        return result;
    }
}
```

JDK 动态代理主要的两个类：==InvocationHandler== 和 ==Proxy==

## 3 - 本Demo
### 文件结构:

- proxy : 静态代理类
- proxy_test : 测试类（客户）
- real_subject : 真实主题类
- subject : 抽象主题类/接口

- proxy_invocation_handler：动态代理Handler类

