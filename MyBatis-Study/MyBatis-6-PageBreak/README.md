# MyBatis-6-PageBreak

## 前言

本Demo主要测试Mybatis中对于分页功能的实现。

### 分页

**什么是分页？为什么要用分页？**

使用SELECT查询时，如果结果集数据量很大，比如几万行数据，放在一个页面显示的话数据量太大，不如分页显示，每次显示100条。

要实现分页功能，实际上就是从结果集中显示第1~100条记录作为第1页，显示第101~200条记录作为第2页，以此类推。

**在MyBatis中使用分页本质上也是使用SQL中的LIMIT**

## 直接使用 SQL语句 LIMIT 实现分页

**流程：接口  ---->  Mapper.xml配置文件 ---->  测试类**

- 接口

```java
public interface UserMapper {


    /**
     * Select users by limit
     *
     * @param map the map
     * @return the list
     */
    List<Users> selectUsersByLimit(Map<String,Integer> map);

}
```

- Mapper.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="top.calvinhaynes.mapper.UserMapper">

    <!--方式一：利用LIMIT实现分页查询-->
    <select id="selectUsersByLimit" resultType="users" parameterType="map">
        select * from users limit #{startIndex},#{pageSize}
    </select>

</mapper>
```

- 测试

```java
/**
* 分页查询用户测试 LIMIT
*/
@Test
public void selectUserByLimitTest() {
    SqlSession session = MyBatisUtils.getSession();

    UserMapper mapper = session.getMapper(UserMapper.class);

    HashMap<String, Integer> map = new HashMap<>();
    map.put("startIndex",0);
    map.put("pageSize",2);

    List<Users> users = mapper.selectUsersByLimit(map);

    for (Users user : users) {
        System.out.println(user);
    }

    session.close();
}
```

## 使用Java类 RowBounds 实现分页

首先，这种方式不如直接使用LIMIT的SQL语句方便快捷，但是SQL语句会更加简单，实现分页将由外部Java类 RowBounds来实现

- 接口

```java
/**
* Select users by row bounds .
*
* @return the list
*/
List<Users> selectUsersByRowBounds();
```

- Mapper.xml

```xml
<!--方式二：使用RowBounds类实现分页查询-->
<select id="selectUsersByRowBounds" resultType="users">
    select * from users
</select>
```

- 测试

```java
/**
     * 使用RowBounds类实现分页查询测试
     */
@Test
public void selectUserByRowBoundsTest(){
    SqlSession session = MyBatisUtils.getSession();

    RowBounds rowBounds = new RowBounds(0, 2);
    List<Users> usersList = 		  		session.selectList("top.calvinhaynes.mapper.UserMapper.selectUsersByRowBounds", null, rowBounds);

    for (Users users : usersList) {
        System.out.println(users);
    }
    session.close();
}
```

## MyBatis 分页插件 PageHelper

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.6an4xxb6dzk0.png)

