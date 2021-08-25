# Spring事务

## 前言

Spring在不同的事务管理API之上定义了一个抽象层，使得开发人员不必了解底层的事务管理API就可以使用Spring的事务管理机制。Spring支持编程式事务管理和声明式的事务管理。

### **编程式事务管理**（不用）

- 将事务管理代码嵌到业务方法中来控制事务的提交和回滚

- 缺点：必须在每个事务操作业务逻辑中包含额外的事务管理代码

### 声明式事务管理

- 一般情况下比编程式事务好用。
- 将事务管理代码从业务方法中分离出来，以声明的方式来实现事务管理。
- 将事务管理作为横切关注点，通过aop方法模块化。Spring中通过Spring AOP框架支持声明式事务管理。

## Spring事务管理实战

### 1 - **使用Spring管理事务，注意头文件的约束导入 : tx，aop**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">
   
</beans>
```

### 2 - JDBC事务管理器配置

```xml
<!--JDBC事务管理器配置-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

### 3 - 配置事务通知

```xml
<!--结合AOP实现事务的织入-->
<!--配置事务通知-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <!--给哪些方法配置事务-->
    <!--配置事务的传播特性： propagation: REQUIRED-->
    <tx:attributes>
        <tx:method name="addUser" propagation="REQUIRED"/>
        <tx:method name="deleteUserById" propagation="REQUIRED"/>
        <tx:method name="updateUserByName" propagation="REQUIRED"/>
        <tx:method name="queryUser" propagation="REQUIRED"/>
        <tx:method name="*" propagation="REQUIRED"/>
    </tx:attributes>
</tx:advice>
```

#### 事务配置的传播特性：propagation属性

1. 简述
        在声明式的事务处理中，要配置一个切面，其中就用到了propagation，表示打算对这些方法怎么使用事务，是用还是不用，其中propagation有七种配置，REQUIRED、SUPPORTS、MANDATORY、REQUIRES_NEW、NOT_SUPPORTED、NEVER、NESTED。默认是REQUIRED。

2. Spring中七种Propagation类的事务属性详解：

- **REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。** 
- SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。 
-  MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。 
- REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。 
- NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
- NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。 
- **NESTED：支持当前事务，如果当前事务存在，则执行一个嵌套事务，如果当前没有事务，就新建一个事务。**


3. 注意事项
这些配置将影响数据存储，必须根据情况选择。

### 4 - 配置AOP

```xml
<aop:config>
    <aop:pointcut id="txPointCut" expression="execution(* top.calvinhaynes.mapper.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut" />
</aop:config>
```

### 5 - 测试

```java
@Test
public void test() {
    ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    UserMapper mapper = context.getBean("userMapper", UserMapper.class);

    List<Users> usersList = mapper.comprehensiveUser();

    for (Users users : usersList) {
        System.out.println(users);
    }
}
```

**思考：**

为什么需要事务？

- 如果不配置事务，可能存在数据提交不一致的情况；
- 如果我们不在Spring中去配置声明式事务，我们就需要在代码中手动配置事务！
- 事务在项目的开发中十分重要，涉及到数据的一致性和完整性问题，不容马虎！
