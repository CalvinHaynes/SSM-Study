# Demo_IOC_7

本Demo主要基于 Demo_IOC_6 的基础上测试了常用的 Spring 配置

如下：

- 别名
```xml
 <!--设置别名：在获取Bean的时候可以使用别名获取-->
    <!--可以设置多个别名 -->
    <alias name="userService" alias="service"/>
    <alias name="userService" alias="myService"/>
```

- bean 的配置
```xml
 <!--bean就是java对象,由Spring创建和管理-->
    <!--
        id 是bean的标识符,要唯一,如果没有配置id,name就是默认标识符
        如果配置id,又配置了name,那么name是别名
        name可以设置多个别名,可以用逗号,分号,空格隔开
        如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;

        class是bean的全限定名=包名+类名
    -->
    <bean id="userService" name="test,test1,test2" class="top.calvinhaynes.service.UserServiceImpl">
        <constructor-arg name="userDao" ref="oracleImpl"/>
    </bean>
```

- import 
```xml
<!--团队合作最后汇总配置文件，集成为一个配置文件的时候 -->
<!--可以导入团队得所有的配置文件到一个总的配置文件applicationContext.xml中 -->
<!--然后项目主程序只解析这一个总的XML配置文件就行了 -->
    <import resource="beans.xml"/>
```