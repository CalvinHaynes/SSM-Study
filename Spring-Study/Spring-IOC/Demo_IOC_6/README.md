# Demo_IOC_6

本 Demo 就是主要测试了有参构造器在 XML 配置文件中的赋值操作

共有三种方式：
```xml
    <!--方式一：使用name-ref的方式设置有参构造器参数 -->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl">
        <constructor-arg name="userDao" ref="oracleImpl"/>
    </bean>
    

    <!--方式二：使用下标设置有参构造器参数 -->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl">
        <constructor-arg index="0" ref="mysqlImpl"/>
    </bean>


    <!--方式三：使用参数种类 type 设置有参构造器参数 （非常不推荐，因为多个参数的有参构造器参数可能有多个相同类型的参数）-->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl">
        <constructor-arg type="top.calvinhaynes.dao.UserDao" ref="userDaoImpl"/>
    </bean>

```
