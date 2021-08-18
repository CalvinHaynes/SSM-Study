# MyBatis-2-FirstDemo

## 前言

### 什么是 MyBatis？

MyBatis 是一款优秀的**持久层**框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 **XML** 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

MyBatis 本是apache的一个[开源项目](https://baike.baidu.com/item/开源项目/3406069)iBatis, 2010年这个[项目](https://baike.baidu.com/item/项目/477803)由apache software foundation 迁移到了[google code](https://baike.baidu.com/item/google code/2346604)，并且改名为MyBatis 。2013年11月迁移到[Github](https://baike.baidu.com/item/Github/10145341)。

iBATIS一词来源于“internet”和“abatis”的组合，是一个基于Java的[持久层](https://baike.baidu.com/item/持久层/3584971)框架。iBATIS提供的持久层框架包括SQL Maps和Data Access Objects（DAOs）

当前，最新版本是MyBatis 3.5.7 ，其发布时间是2021年4月21日。

**官方中文文档位置**：https://mybatis.org/mybatis-3/zh/

官方文档中非常详细，其他非重点内容在本文不涉及，自行查找

### 特点

- 简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现。
- 灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。 sql写在xml里，便于统一管理和优化。通过sql语句可以满足操作数据库的所有需求。
- 解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。
- 提供映射标签，支持对象与数据库的orm **(Object Relationship Mapping)**字段关系映射
- 提供对象关系映射标签，支持对象关系组建维护
- 提供xml标签，支持编写动态sql。 [2] 

### 持久化和持久层

#### 1 - 持久化

**持久化是将程序数据在持久状态和瞬时状态间转换的机制。**

- 即把数据（如内存中的对象）保存到可永久保存的存储设备中（如磁盘）。持久化的主要应用是将内存中的对象存储在数据库中，或者存储在磁盘文件中、XML数据文件中等等。

- JDBC就是一种持久化机制。文件IO也是一种持久化机制。

- 在生活中 : 将鲜肉冷藏，吃的时候再解冻的方法也是。将水果做成罐头的方法也是。

**为什么需要持久化服务呢？那是由于内存本身的缺陷引起的**

- ==内存断电后数据会丢失==，但有一些对象是无论如何都不能丢失的，比如银行账号等，遗憾的是，人们还无法保证内存永不掉电。

- ==内存过于昂贵==，与硬盘、光盘等外存相比，内存的价格要高2~3个数量级，而且维持成本也高，至少需要一直供电吧。所以即使对象不需要永久保存，也会因为内存的容量限制不能一直呆在内存中，需要持久化来缓存到外存。

#### 2 - 持久层

DAO层，Service层，Controller层

- 其中DAO层（Data Access Object 数据访问对象）是完成持久化工作的对象
- 持久化实现过程大多都是由各种关系数据库完成
- 不过这里有一个字需要特别强调，也就是所谓的“层”。对于应用系统而言，数据持久功能大多是必不可少的组成部分。也就是说，我们的系统中，已经天然的具备了“持久层”概念？也许是，但也许实际情况并非如此。之所以要独立出一个“持久层”的概念,而不是“持久模块”，“持久单元”，也就意味着，我们的系统架构中，应该有一个**相对独立的逻辑层面，专注于数据持久化逻辑的实现.**
- 与系统其他部分相对而言，这个层面应该具有一个较为清晰和严格的逻辑边界。【说白了就是用来操作数据库存在的！】
  

## 第一个Demo

### 流程图：

![MyBatis第一个Demo思路](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/MyBatis第一个Demo思路.5jtp490xpsk0.png)

### 1 - 搭建环境

**MySQL数据库的环境**：

本Demo的 `MyBatis-Study/MyBatis-2-FirstDemo/src/main/resources/sql`就是整个测试数据库的sql语句

**Maven导入MyBatis相关包**：

```xml
<dependencies>

        <!--MySQL驱动包 -->
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.26</version>
        </dependency>

        <!--MyBatis包-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>

        <!--Junit包-->
        <!-- https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

    </dependencies>
```

### 2 - 编写MyBatis核心XML配置文件

在写XML文件之前，确保IDEA能自动识别XML文件，这样才能根据XML文件头的DTD约束正确使用相应的配置文件

建议写两个code templates，这样以后方便直接创建MyBatis的配置文件

Templates中的内容在下面，复制粘贴即可

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.74xx6amhjic0.png)

**mybatis-config.xml**：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```

**mybatis-mapper.xml**：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.mybatis.example.BlogMapper">

</mapper>
```

如果发现没有XML代码提示的话，说明DTD约束文件没有导入，当然，如果用Maven正常添加完依赖后不会出现这种问题，可以去看看Maven的MyBatis的jar包中两个dtd约束文件所在位置

本项目的位置就是：

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.2c2ss6wakzms.png)

#### 写核心配置文件的注意点

- **mappers标签**

mappers标签下的mapper配置文件一定要在之后写完mapper的XML配置文件之后修改成自己的，否则一定会报注册错误（is not known to the MapperRegistry.），因为你没有在MyBatis框架的核心配置文件中引入mapper配置

```xml
  <mappers>
    <mapper resource="改为当前项目的Mapper配置文件位置"/>
  </mappers>
```

- **Could not find resource top/calvinhaynes/dao/mybatis-mapper.xml**：

**Maven静态资源过滤问题**：IDEA是不会编译src的java目录的xml文件，所以在Mybatis的配置文件中找不到xml文件！

==加入以下配置到pom.xml中即可解决==：

```xml
<!--解决Maven静态资源过滤问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

### 3 - 编写MyBatis工具类

```java
package top.calvinhaynes.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ProjectName: MyBatisUtils
 * @Author: CalvinHaynes
 * @Date: 2021/8/17 0:53
 * @Description:MyBatis工具类
 */
public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //从 XML 中构建 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream= Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从SqlSessionFactory 中获取 SqlSession
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }

}

```

### 4 - POJO 实体类编写

```java
package top.calvinhaynes.pojo;

/**
 * @ProjectName: User
 * @Author: CalvinHaynes
 * @Date: 2021/8/17 0:59
 * @Description:与数据库数据相关的实体类
 */
public class Users {
    private int id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Users() {
    }
}

```

### 5 - Mapper接口编写（相当于DAO接口）

```java
package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;


public interface UserMapper {
    List<Users> selectUser();
}

```



### 6 - 编写Mapper XML配置文件（相当于DAO接口的实现类）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--相当于 UserMapper 接口的实现类，只不过是XML写法-->
<!-- mapper namespace属性=实现的接口-->
<mapper namespace="top.calvinhaynes.mapper.UserMapper">

    <!-- 相当于一个返回值为 Users 类型的方法 selectUser-->
    <select id="selectUser" resultType="top.calvinhaynes.pojo.Users">
        -- 要执行的SQL语句编写位置
        select * from users
    </select>
    
</mapper>
```

### 7 - 测试类

```java
package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.List;

public class Test1 {
    @Test
    public void selectUserTest(){

        //先利用工具类拿到SqlSession
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Users> users = mapper.selectUser();

        for (Users user : users) {
            System.out.println(user);
        }

        session.close();
    }
}

```

## 扩展第一个Demo

### **CRUD的实现**：

#### **添加一个方法的流程：**

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.776ntynyruo0.png)

#### 1 - 接口中添加抽象方法

```java
package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;

/**
 * @ProjectName: UserMapper
 * @Author: CalvinHaynes
 * @Date: 2021/8/18 12:56
 * @Description:UserMapper接口(CRUD）
 */
public interface UserMapper {
    //查询所有User
    List<Users> selectUser();

    //按ID查询User
    Users selectUserById(int i);

    //通过用户名修改用户
    int updateUserByName(Users user);

    //增加用户
    int addUser(Users user);

    //通过id删除一个用户
    int deleteUserById(int id);

}

```

#### 2 - 编写 Mapper 的 XML 配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--相当于 UserMapper 接口的实现类，只不过是XML写法-->
<!-- mapper namespace属性=实现的接口-->
<mapper namespace="top.calvinhaynes.mapper.UserMapper">

    <!-- 相当于一个返回值为 Users 类型的方法 selectUser-->
    <select id="selectUser" resultType="top.calvinhaynes.pojo.Users">
        -- 要执行的SQL语句编写位置
        select * from users
    </select>

    <!--通过用户ID查询用户-->
    <select id="selectUserById" resultType="top.calvinhaynes.pojo.Users" parameterType="int">
        select * from users where id=#{id};
    </select>

    <!--通过用户名修改一个用户-->
    <update id="updateUserByName" parameterType="top.calvinhaynes.pojo.Users">
        update users
        set id = #{id},password = #{pwd}
        where name = #{name};
    </update>

    <!--添加用户-->
    <insert id="addUser" parameterType="top.calvinhaynes.pojo.Users">
        insert into users (id, name, password)
        values (#{id},#{name},#{pwd});
    </insert>

    <!--通过用户ID删除一个用户-->
    <delete id="deleteUserById" parameterType="int">
        delete
        from users
        where id = #{id};
    </delete>

</mapper>
```

#### 3 - 写测试类

```java
package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.List;
import java.util.Set;

public class Test1 {
    @Test
    public void selectUserTest(){

        //先利用工具类拿到SqlSession
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Users> users = mapper.selectUser();

        for (Users user : users) {
            System.out.println(user);
        }

        //关闭SqlSession
        session.close();
    }

    @Test
    public void selectUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1000);
        System.out.println(user);

        session.close();
    }

    @Test
    public void updateUserByNameTest(){
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users calvin = new Users(1003,"Calvin","987654");
        int res = mapper.updateUserByName(calvin);

        if(res > 0){
            System.out.println("更改用户" + calvin.getName() + "的信息成功");
        }

        //增删改一定要提交事务
        session.commit();
        session.close();
    }

    @Test
    public void addUserTest(){
        SqlSession session = MyBatisUtils.getSession();

        Users liHua = new Users(1004, "LiHua", "5418814");
        UserMapper mapper = session.getMapper(UserMapper.class);
        int res = mapper.addUser(liHua);

        if(res > 0){
            System.out.println("添加用户" + liHua + "成功");
        }

        session.commit();
        session.close();
    }

    @Test
    public void deleteUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.deleteUserById(1004);

        if(i > 0){
            System.out.println("删除用户成功");
        }

        session.commit();
        session.close();
    }
}

```

## 使用Map

一句话，如果我们的实体类，或者数据库中的表，字段或者参数过多，我们应该考虑使用Map!

### 1 - UserMapper接口

```java
//利用Map添加一个用户
int addUserByMap(Map map);
```

### 2 - Mapper 的 XML 配置文件

```xml
<insert id="addUserByMap" parameterType="map">
    insert into users (id, name, password, last_name)
    values (#{userId},#{userName},#{userPassword},#{userLastName});
</insert>
```

### 3 - 测试方法

```java
 @Test
    public void addUserByMapTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);


        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 1005);
        map.put("userName", "Calvin");
        map.put("userPassword", "888888");
        map.put("userLastName","Durant");

        int i = mapper.addUserByMap(map);

        if(i > 0){
            System.out.println("利用Map作为参数添加用户成功");
        }

        session.commit();
        session.close();
    }
```

## 模糊查询在MyBatis中的实现

### **方式一**：

==需要在调用处手动的去添加"%"通配符==

#### 1 - 接口

```java
//根据姓名关键字模糊查询
List<Users> selectUserLike(String value);
```

#### 2 - 配置文件

```xml
<!--模糊查询-->
<select id="selectUserLike" resultType="top.calvinhaynes.pojo.Users">
        <!--方式一：需要在调用处手动的去添加“%”通配符-->
        select * from users where name like #{value}
</select>
```

#### 3 - 测试方法

```java
@Test
    public void selectUserLikeTest(){
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        //方式一：需要在调用处手动的去添加"%"通配符
        List<Users> usersList = mapper.selectUserLike("%i%");

        for (Users users : usersList) {
            System.out.println(users);
        }

        session.close();
    }
```

### 方式二：

利用MySQL函数CONCAT连接字符串

#### 1 - 接口

```java
//根据姓名关键字模糊查询
List<Users> selectUserLike(String value);
```

#### 2 - 配置文件

```xml
<!--模糊查询-->
<select id="selectUserLike" resultType="top.calvinhaynes.pojo.Users">
    
    <!--方式二：CONCAT合并字符串的MySQL函数，无需在调用处再手动添加通配符了 -->
    select *
    from users
    where name like concat('%',#{value},'%');

</select>
```

```xml
<!--模糊查询-->
<select id="selectUserLike" resultType="top.calvinhaynes.pojo.Users">
    
    <!--方式二：CONCAT合并字符串的MySQL函数，无需在调用处再手动添加通配符了 -->
    select *
    from users
    where name like concat('%','${value}','%');

</select>
```

- \#{ }是预编译处理，MyBatis在处理#{ }时，它会将sql中的#{ }替换为？，然后调用**PreparedStatement**的set方法来赋值，传入字符串后，会在值两边加上单引号，使用占位符的方式提高效率，可以防止sql注入。

- ${}:表示拼接sql串，将接收到参数的内容不加任何修饰拼接在sql中，可能引发**sql注入**。

#### 3 - 测试方法

```java
 @Test
    public void selectUserLikeTest(){
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        //方式二：配置文件已经添加了"%"通配符
        List<Users> usersList = mapper.selectUserLike("i");

        for (Users users : usersList) {
            System.out.println(users);
        }

        session.close();
    }
```

## 作用域（Scope）和生命周期

理解我们之前讨论过的不同作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的并发问题。

### 流程分析：

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.7ho2olkwl8w0.png)

### SqlSessionFactoryBuilder

这个类可以被实例化、使用和丢弃，==一旦创建了 SqlSessionFactory，就不再需要它了==。

 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是==局部方法变量==）。

 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但最好还是不要一直保留着它，以保证所有的 XML 解析资源可以被释放给更重要的事情。

### SqlSessionFactory（数据库连接池）

==SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在==，没有任何理由丢弃它或重新创建另一个实例。 

使用 SqlSessionFactory 的最佳实践是==在应用运行期间不要重复创建多次==，多次重建 SqlSessionFactory 被视为一种代码“坏习惯”。

因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用**单例模式**或者**静态单例模式**。

### SqlSession（连接到连接池的一个请求）

每个线程都应该有它自己的 SqlSession 实例。

SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。 

绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。

 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。 

如果你现在正在使用一种 Web 框架，考虑将 SqlSession 放在一个和 HTTP 请求相似的作用域中。

 换句话说，每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它。

 ==这个关闭操作很重要==，为了确保每次都能执行关闭操作，你应该把这个关闭操作放到 finally 块中。 下面的示例就是一个确保 SqlSession 关闭的标准模式：

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
  // 你的应用逻辑代码
}
```

在所有代码中都遵循这种使用模式，可以保证所有数据库资源都能被正确地关闭。

![引用自狂神说公众号](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.2hj4x8hixa00.png)

### 映射器实例

映射器是一些绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。虽然从技术层面上来讲，任何映射器实例的最大作用域与请求它们的 SqlSession 相同。但方法作用域才是映射器实例的最合适的作用域。 也就是说，映射器实例应该在调用它们的方法中被获取，使用完毕之后即可丢弃。 映射器实例并不需要被显式地关闭。尽管在整个请求作用域保留映射器实例不会有什么问题，但是你很快会发现，在这个作用域上管理太多像 SqlSession 的资源会让你忙不过来。 因此，最好将映射器放在方法作用域内。就像下面的例子一样：

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
  BlogMapper mapper = session.getMapper(BlogMapper.class);
  // 你的应用逻辑代码
}
```

