# MyBatis-7-AnnotationDevelopment

## 前言

### 面向接口编程

- 之前学过面向对象编程，也学习过接口，但在真正的开发中，很多时候会选择面向接口编程。
- 根本原因：解耦，可拓展，提高复用，分层开发中，上层不用管具体的实现，大家都遵守共同的标准，使得开发变得容易，规范性更好
- 在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的对系统设计人员来讲就不那么重要了；
- 而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。

### 使用注解操作MyBatis优缺点

使用注解来映射简单语句会使代码显得更加简洁，但对于稍微复杂一点的语句，Java 注解不仅力不从心，还会让你本就复杂的 SQL 语句更加混乱不堪。 因此，如果你需要做一些很复杂的操作，最好用 XML 来映射语句。

## 注解开发实战

使用注解之后就可以抛弃Mapper.xml配置文件了，直接在接口中的抽象方法上写注解就可以实现CURD了

### CURD

- 接口

```java
package top.calvinhaynes.mapper;

import org.apache.ibatis.annotations.*;
import top.calvinhaynes.pojo.Users;

import java.util.List;


/**
 * 用户映射器
 *
 * @author CalvinHaynes
 * @date 2021 /08/19
 */
public interface UserMapper {
    /**
     * 查询所有User
     *
     * @return {@link List}<{@link Users}>
     */
    @Select("select * from users")
    List<Users> getAllUsers();

    /**
     * 按ID查询User
     *
     * @param id id
     * @return {@link Users}
     */
    @Select("select * from users where id = #{id}")
    Users selectUserById(@Param("id") int id);

    /**
     * Add user
     *
     * @param user the user
     * @return the int
     */
    @Insert("insert into users (id,name,password) values (#{id},#{name},#{password})")
    int addUser(Users user);


    /**
     * Delete user by id
     *
     * @param id the id
     * @return the int
     */
    @Delete("delete from users where id = #{uid}")
    int deleteUserById(@Param("uid") int id);


    /**
     * Update user by name
     *
     * @param user the user
     * @return the int
     */
    @Update("update users set id = #{id},password = #{password} where name = #{name}")
        int updateUserByName(Users user);
}

```

- 测试类

```java
package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.List;

/**
 * 所有测试
 *
 * @author CalvinHaynes
 * @date 2021 /08/19
 */
public class AllTest {

    /**
     * 查询所有用户测试
     */
    @Test
    public void selectUserTest() {

        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Users> users = mapper.getAllUsers();

        for (Users user : users) {
            System.out.println(user);
        }

        session.close();
    }

    /**
     * 通过用户id查询用户信息测试
     */
    @Test
    public void selectUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        session.close();
    }

    /**
     * Add user test.
     */
    @Test
    public void addUserTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int user = mapper.addUser(new Users(1006, "James", "89098766"));

        if (user > 0) {
            System.out.println("添加成功");
        }

        session.close();
    }

    /**
     * Delete user by id test.
     */
    @Test
    public void deleteUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int user = mapper.deleteUserById(1006);

        if (user > 0) {
            System.out.println("删除成功");
        }

        session.close();
    }

    /**
     * Update user by name test.
     */
    @Test
    public void updateUserByNameTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int user = mapper.updateUserByName(new Users(1007, "LiHua", "8905467577"));

        if (user > 0) {
            System.out.println("更新成功");
        }

        session.close();
    }


}

```

### @param注解

- 基本类型的参数或者String类型，需要加上
- 引用类型不需要加
- 如果只有一个基本类型的话，可以忽略，但是建议加上
- 在SQL中引用的就是@Param()中设定的属性名

