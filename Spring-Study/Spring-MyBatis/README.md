# Spring-MyBatis

## 前言

MyBatis-Spring 会帮助你将 MyBatis 代码无缝地整合到 Spring 中。

文档链接：http://mybatis.org/spring/zh/index.html

**导入Maven依赖：**

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.2</version>
</dependency>
```

## 整合方式一：sqlSessionTemplate

### 1 - 引入Spring配置文件spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

### 2 - 配置数据源替换mybatis的数据源

`SqlSessionFactory` 需要一个 `DataSource`（数据源）。这可以是任意的 `DataSource`，只需要和配置其它 Spring 数据库连接一样配置它就可以了。

```xml
 <!--DataSource:使用Spring的数据源替换Mybatis的配置 c3p0 dbcp druid
我们这里使用Spring提供的JDBC：-->
 <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
     <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
     <property name="url"
               value="jdbc:mysql://localhost:3306/exam1?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
     <property name="username" value="root"/>
     <property name="password" value="412523chx"/>
 </bean>
```

### 3 - 配置SqlSessionFactory，关联MyBatis

- 在基础的 MyBatis 用法中，是通过 `SqlSessionFactoryBuilder` 来创建 `SqlSessionFactory` 的。而在 MyBatis-Spring 中，则使用 `SqlSessionFactoryBean` 来创建。

- `SqlSessionFactory` 有一个唯一的必要属性：用于 JDBC 的 `DataSource`。这可以是任意的 `DataSource` 对象，它的配置方法和其它 Spring 数据库连接是一样的。

- 需要注意的是，这个配置文件**并不需要**是一个完整的 MyBatis 配置。确切地说，任何环境配置（`<environments>`），数据源（`<DataSource>`）和 MyBatis 的事务管理器（`<transactionManager>`）都会被**忽略**。 `SqlSessionFactoryBean` 会创建它自有的 MyBatis 环境配置（`Environment`），并按要求设置自定义环境的值。

- 如果 MyBatis 在映射器类对应的路径下找不到与之相对应的映射器 XML 文件，那么也需要配置文件。这时有两种解决办法：==第一种是手动在 MyBatis 的 XML 配置文件中的 `<mappers>` 部分中指定 XML 文件的类路径==；第二种是设置工厂 bean 的 `mapperLocations` 属性。

`mapperLocations` 属性接受多个资源位置。这个属性可以用来指定 MyBatis 的映射器 XML 配置文件的位置。

```xml
<!--sqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <!--关联mybatis配置文件-->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath:com/top/mapper/*.xml"/>
</bean>
```

### ==4 - 注册sqlSessionTemplate，关联sqlSessionFactory==

在 MyBatis 中，你可以使用 `SqlSessionFactory` 来创建 `SqlSession`。 一旦你获得一个 session 之后，你可以使用它来执行映射了的语句，提交或回滚连接，最后，当不再需要它的时候，你可以关闭 session。 使用 MyBatis-Spring 之后，你不再需要直接使用 `SqlSessionFactory` 了，因为你的 bean 可以被注入一个线程安全的 `SqlSession`，它能基于 Spring 的事务配置来自动提交、回滚、关闭 session。

- `SqlSessionTemplate` 是 MyBatis-Spring 的核心。作为 `SqlSession` 的一个实现，这意味着可以使用它无缝代替你代码中已经在使用的 `SqlSession`。 `SqlSessionTemplate` 是线程安全的，可以被多个 DAO 或映射器所共享使用。
- 当调用 SQL 方法时（包括由 `getMapper()` 方法返回的映射器中的方法），`SqlSessionTemplate` 将会保证使用的 `SqlSession` 与当前 Spring 的事务相关。 此外，它管理 session 的生命周期，包含必要的关闭、提交或回滚操作。另外，它也负责将 MyBatis 的异常翻译成 Spring 中的 `DataAccessExceptions`。
- 由于模板可以参与到 Spring 的事务管理中，并且由于其是线程安全的，可以供多个映射器类使用，你应该**总是**用 `SqlSessionTemplate` 来替换 MyBatis 默认的 `DefaultSqlSession` 实现。在同一应用程序中的不同类之间混杂使用可能会引起数据一致性的问题。
- 可以使用 `SqlSessionFactory` 作为构造方法的参数来创建 `SqlSessionTemplate` 对象。

```xml
<!--整合方式一：注册sqlSessionTemplate，关联sqlSessionFactory-->
<!--相当于过去MyBatis中用sqlSessionFactory创建sqlSession-->
<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能使用构造器注入sqlSessionFactory,因为它没有set方法-->
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>
```

### 5 - 需要UserMapper接口的UserMapperImpl 实现类，私有化sqlSessionTemplate

```java
package top.calvinhaynes.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import top.calvinhaynes.pojo.Users;

import java.util.List;

/**
 * 用户映射器impl
 * The type User mapper.
 *
 * @author CalvinHaynes
 * @date 2021 /08/23
 */
public class UserMapperImpl implements UserMapper{


    /**
     * 我们的所有操作，在原来都使用sqlSession来执行，现在都使用SqlsessionTemplate
     * 单例模式 私有化
     * */
    private SqlSessionTemplate sqlSession;

    /**
     * Sets sql session.
     *
     * @param sqlSession the sql session
     */
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 得到所有用户
     *
     * @return {@link List}<{@link Users}>
     */
    @Override
    public List<Users> getAllUsers() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getAllUsers();
    }
    
}
```

### 6 - 将自己写的实现类，注入到Spring配置文件中。

```xml
<bean id="userMapper" class="top.calvinhaynes.mapper.UserMapperImpl">
    <property name="sqlSession" ref="sqlSessionTemplate"/>
</bean>
```

## 整合方式二：SqlSessionDaoSupport

`SqlSessionDaoSupport` 是一个抽象的支持类，用来为你提供 `SqlSession`。调用 `getSqlSession()` 方法你会得到一个 `SqlSessionTemplate`，之后可以用于执行 SQL 方法。

### 1 - 继承 SqlSessionDaoSupport 抽象支持类

```java
/**
 * 用户映射器impl2
 * The type User mapper impl 2.
 *
 * @author CalvinHaynes
 * @date 2021/08/23
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<Users> getAllUsers() {
        return getSqlSession().getMapper(UserMapper.class).getAllUsers();
    }
}
```

### 2 - 将自己写的实现类，注入到Spring配置文件中。

`SqlSessionDaoSupport` 需要通过属性设置一个 `sqlSessionFactory` 或 `SqlSessionTemplate`。如果两个属性都被设置了，那么 `SqlSessionFactory` 将被忽略。

```xml
<!--方式二注册bean-->
<bean id="userMapper2" class="top.calvinhaynes.mapper.UserMapperImpl2">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    <!--<property name="sqlSessionTemplate" ref="sqlSessionTemplate"/>-->
</bean>
```

