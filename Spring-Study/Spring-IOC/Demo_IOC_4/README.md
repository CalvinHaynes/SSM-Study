# Demo_IOC_4

## DI 在 Demo_IOC_2 的重现
- 将创建对象交给Spring处理，这次只需配置配置文件即可
- 这次不用我们自己去创建对象实现功能了，只需要写配置文件就行了，Spring 帮我们完成对象的创建、管理和装配

## 本项目具体内容
```xml
    <bean id="mysqlImpl" class="top.calvinhaynes.dao.UserDaoMySQLImpl"/>
    <bean id="oracleImpl" class="top.calvinhaynes.dao.UserDaoOracleImpl"/>

    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl">
        <!--引用另外一个bean , 不是用value 而是用 ref-->
        <!--本质上就是利用set方法实现userDao属性的修改，如果去掉set方法这里会报错 -->
        <property name="userDao" ref="mysqlImpl"/>
    </bean>
```

- 利用配置文件 beans.xml 配置对象，如果想用不同的UserDao实现类的方法，则直接修改id为userService的bean的property即可
- 这里的`<property name="userDao" ref="mysqlImpl"/>`就是调用的UserServiceImpl的set方法