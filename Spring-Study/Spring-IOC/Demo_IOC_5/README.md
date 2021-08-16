# Demo_IOC_5

本 Demo 就是测试了 Spring 创建对象默认是通过无参构造器的方式

```xml
    <bean id="mysqlImpl" class="top.calvinhaynes.dao.UserDaoMySQLImpl"/>
    <bean id="oracleImpl" class="top.calvinhaynes.dao.UserDaoOracleImpl"/>

    <!--IOC 创建对象默认是通过无参构造器的方式-->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl">
        <!--引用另外一个bean , 不是用value 而是用 ref-->
        <!--本质上就是利用set方法实现userDao属性的修改，如果去掉set方法这里会报错 -->
        <property name="userDao" ref="mysqlImpl"/>
    </bean>

```
通过输出结果还可以得出一个结论：

**在配置文件加载的时候。其中管理的对象都已经初始化了！**