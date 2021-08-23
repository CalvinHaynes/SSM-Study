# SQL注入

## 前言

SQL 注入（SQL Injection）是发生在 Web 程序中数据库层的==安全漏洞==，是网站存在最多也是最简单的漏洞。主要原因是程序对用户输入数据的合法性没有判断和处理，导致攻击者可以在 Web 应用程序中事先定义好的 SQL 语句中添加额外的 SQL 语句，在管理员不知情的情况下实现非法操作，以此来实现欺骗数据库服务器执行==非授权的任意查询==，从而进一步获取到数据信息。

简而言之，SQL 注入就是在用户输入的字符串中加入 SQL 语句，如果在设计不良的程序中忽略了检查，那么这些注入进去的 SQL 语句就会被数据库服务器误认为是正常的 SQL 语句而运行，攻击者就可以执行计划外的命令或访问未被授权的数据

![img](http://c.biancheng.net/uploads/allimg/200813/5-200Q31G54Q42.png)

## 原理

SQL 注入的原理主要有以下 4 点：

#### 1）恶意拼接查询

我们知道，SQL 语句可以查询、插入、更新和删除数据，且使用分号来分隔不同的命令。例如：

```sql
SELECT * FROM users WHERE user_id = $user_id
```

其中，user_id 是传入的参数，如果传入的参数值为`“1234; DELETE FROM users”`，那么最终的查询语句会变为：

```sql
SELECT * FROM users WHERE user_id = 1234; DELETE FROM users
```

如果以上语句执行，则会删除 users 表中的所有数据。

#### 2）利用注释执行非法命令。

SQL 语句中可以插入注释。例如：

```sql
SELECT COUNT(*) AS 'num' FROM game_score WHERE game_id=24411 AND version=$version
```

如果 version 包含了恶意的字符串`'-1' OR 3 AND SLEEP(500)--`，那么最终查询语句会变为：

```sql
SELECT COUNT(*) AS 'num' FROM game_score WHERE game_id=24411 AND version='-1' OR 3 AND SLEEP(500)--
```

以上恶意查询只是想耗尽系统资源，SLEEP(500) 将导致 SQL 语句一直运行。如果其中添加了修改、删除数据的恶意指令，那么将会造成更大的破坏。

#### 3）传入非法参数

SQL 语句中传入的字符串参数是用单引号引起来的，如果字符串本身包含单引号而没有被处理，那么可能会篡改原本 SQL 语句的作用。 例如：

```sql
SELECT * FROM user_name WHERE user_name = $user_name
```

如果 user_name 传入参数值为 G'chen，那么最终的查询语句会变为：

```sql
SELECT * FROM user_name WHERE user_name ='G'chen'
```

一般情况下，以上语句会执行出错，这样的语句风险比较小。虽然没有语法错误，但可能会恶意产生 SQL 语句，并且以一种你不期望的方式运行。

#### 4）添加额外条件

在 SQL 语句中添加一些额外条件，以此来改变执行行为。条件一般为真值表达式。例如：

```sql
UPDATE users SET userpass='$userpass' WHERE user_id=$user_id;
```

如果 user_id 被传入恶意的字符串“1234 OR TRUE”，那么最终的 SQL 语句会变为：

```sql
UPDATE users SET userpass= '123456' WHERE user_id=1234 OR TRUE;
```

这将更改所有用户的密码。

## 测试Demo

所在目录结构（**SSM-Study\MyBatis-Study\MyBatis-JDBC\src\test\java\top\calvinhaynes\SQLInjection\Test1.java**）

```java
package top.calvinhaynes.SQLInjection;

import top.calvinhaynes.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ProjectName: Test1
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 23:02
 * @Description:SQL注入测试类
 */
public class Test1 {
    public static void main(String[] args) throws SQLException {
        //恶意拼接查询
        logIn("' or '1=1","' or '1=1");

        //分析结果
        //实际执行语句是：SELECT * FROM users WHERE `NAME`='' or '1=1' AND `PASSWORD`='' or '1=1'
        //这样恶意拼接字符串后，导致 WHERE 后面的语句恒真，自然就会输出所有的 users 信息
    }

    public static void logIn(String name,String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
//        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE `NAME`='" + name + "' AND `PASSWORD`='" + password + "'";

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("实际执行语句是：" + sql + "\n");

            while (resultSet.next()){
                System.out.println("id:" + resultSet.getString("id"));
                System.out.println("Name:" + resultSet.getString("name"));
                System.out.println("Password:" + resultSet.getString("password"));
                System.out.println("============================================");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection,statement, null);
        }
    }


}

```

