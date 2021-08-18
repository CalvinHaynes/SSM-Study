# MyBatis-JDBC

## 前言

SUN 公司为了简化开发人员（对数据库的统一）的操作，提供了一个（Java 操作数据库的 ）规范，称为JDBC。

这些规范的实现由具体的厂商去做

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.4vjtv9uhj7u0.png)

==JDBC 用于 MyBatis 框架中，不用深入学习，简单了解即可==

## 使用

### 1 - **导入MySQL驱动包**

```xml
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.26</version>
</dependency>
```

### 2 - 使用过程

```java
package top.calvinhaynes;

import java.sql.*;

public class JdbcFirstTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.加载驱动（固定写法）
        // DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.用户信息和 URL
        //URL = "协议：//主机地址：端口号/数据库名?参数1&参数2&参数3"
        String url = "jdbc:mysql://localhost:3306/exam1?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String userName = "root";
        String password = "412523chx";

        //3.连接成功,返回数据库对象 connection
        Connection connection = DriverManager.getConnection(url, userName, password);

        //4.创建执行SQL的对象 statement
        Statement statement = connection.createStatement();

        //5.执行SQL的对象去执行SQL,如果有本程序需要用的数据操作，再在本此处进行操作

        //要执行的SQL语句
        String sql = "SELECT * FROM customers";

        //执行SQL语句,拿到返回结果
        ResultSet resultSet = statement.executeQuery(sql);

        //打印一下返回结果看看效果
        while(resultSet.next()){
            System.out.println("cust_id=" + resultSet.getObject("cust_id"));
            System.out.println("cust_name=" + resultSet.getObject("cust_name"));
            System.out.println("cust_address=" + resultSet.getObject("cust_address"));
            System.out.println("cust_city=" + resultSet.getObject("cust_city"));
            System.out.println("cust_state=" + resultSet.getObject("cust_state"));
            System.out.println("cust_zip=" + resultSet.getObject("cust_zip"));
            System.out.println("cust_country=" + resultSet.getObject("cust_country"));
            System.out.println("cust_contact=" + resultSet.getObject("cust_contact"));
            System.out.println("cust_email=" + resultSet.getObject("cust_email"));
            System.out.println("==================================================");
        }

        //6.关闭数据库连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

```

#### 1 - 加载驱动

```java
 //1.加载驱动（固定写法）
// DriverManager.registerDriver(new Driver());
Class.forName("com.mysql.cj.jdbc.Driver");
```

`DriverManager.registerDriver(new Driver());`这种写法被弃用的原因看Driver类源码：

```java
public class Driver extends NonRegisteringDriver implements java.sql.Driver {
    public Driver() throws SQLException {
    }

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }
}

```

Diver 类中就是一个静态的代码块，直接`Class.forName`一下就可以执行`DriverManager.registerDriver(new Driver());`了，还加入了`try catch` 异常处理

#### 2 - 用户信息和URL

```java
//2.用户信息和 URL
//URL = "协议：//主机地址：端口号/数据库名?参数1&参数2&参数3"
String url="jdbc:mysql://localhost:3306/exam1useUnicode=true&characterEncoding=utf8&useSSL=true";
String userName = "root";
String password = "412523chx";
```

#### 3 - 获取数据库对象 connection

```java
//3.连接成功,返回数据库对象 connection
Connection connection = DriverManager.getConnection(url, userName, password);
```

**connection可以进行的操作**

```java
//数据库设置自动提交，事务提交，事务回滚
//只要是数据库可以进行的操作他都可以
connection.rollback();
connection.commit();
connection.setAutoCommit();
....
```

#### 4 - 创建执行SQL的对象 statement

```java
//4.创建执行SQL的对象 statement
Statement statement = connection.createStatement();
```

**statement 可以进行的操作**

```java
String sql = "SQL语句";

statement.execute(sql);//执行任何的SQL语句
statement.executeQuery(sql);//执行查询SQL语句，返回ResultSet对象
statement.executeUpdate(sql);//执行增删改SQL语句，返回受影响行数
```

#### 5 - 查询结果集 ResultSet类

```java
//执行SQL语句,拿到返回结果
ResultSet resultSet = statement.executeQuery(sql);
```

**获取指定的数据类型**

```java
//任意数据类型的数据都可以获得，用于未知数据类型时
resultSet.getObject();
//获取指定的数据类型的数据
resultSet.getString();
resultSet.getInt();
resultSet.getFloat();
resultSet.getDate();
resultSet.getArray();
```

**指针，用于遍历**

```java
resultSet.beforeFirst();	//移动到最前面
resultSet.afterLast();		//移动到最后面
resultSet.next();		//最常用，向下移动一个数据
resultSet.previous();	//移动到前一行
resultSet.absolute(行数)	//移动到指定行
```

#### 6 - 释放资源

```java
//6.关闭数据库连接
resultSet.close();
statement.close();
connection.close();
```

### 3 - 利用Statement类写一个工具类

```java
package top.calvinhaynes.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ProjectName: JdbcUtils
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 20:14
 * @Description:
 */
public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {

        try {
            //getResourceAsStream:返回用于读取指定资源的输入流
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");

            //创建一个没有默认值的空属性列表.
            Properties properties = new Properties();

            //断言 inputStream 是否为空，如果为空抛异常 AssertionError
            assert inputStream != null;

            //加载 db.properties 中的属性列表
            properties.load(inputStream);

            //在此属性列表中搜索具有指定键的属性。
            //如果在此属性列表中找不到该键，则递归地检查默认属性列表及其默认值。
            //如果未找到该属性，该方法将返回null
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //驱动只加载一次
            Class.forName(driver);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接的静态工具方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    //释放资源的静态工具方法
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet!=null){
            resultSet.close();
        }
        if (statement!=null){
            statement.close();
        }
        if (connection!=null){
            connection.close();
        }
    }

}

```

### 4 - 四个测试工具类的测试类（CRUD）

#### 1 - Create 增加

```java
package top.calvinhaynes.CRUD;

import top.calvinhaynes.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ProjectName: JdbcTest2
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 20:54
 * @Description:利用JdbcUtils工具类进行测试增删改查中的增
 */
public class JdbcTestCreate {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
//        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "INSERT INTO orderitems(order_num, order_item, prod_id, quantity, item_price)\n" +
                    "VALUES (20009,8,'FB',20,23.99);";

            int i = statement.executeUpdate(sql);
            if(i > 0){
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection,statement, null);
        }
    }
}

```

#### 2 - Delete 删除

```java
package top.calvinhaynes.CRUD;

import top.calvinhaynes.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ProjectName: JdbcTestDelete
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 21:01
 * @Description:利用JdbcUtils工具类进行测试增删改查中的删
 */
public class JdbcTestDelete {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
//        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "DELETE FROM exam1.orderitems WHERE order_num = 20009 AND order_item = 8";

            int i = statement.executeUpdate(sql);
            if(i > 0){
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection,statement, null);
        }
    }
}

```

#### 3 - Update 更改

```java
package top.calvinhaynes.CRUD;

import top.calvinhaynes.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ProjectName: JdbcTestUpdate
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 21:14
 * @Description:利用JdbcUtils工具类进行测试增删改查中的改
 */
public class JdbcTestUpdate {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
//        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "UPDATE exam1.orderitems t SET t.item_price = 5.50 WHERE t.order_num = 20008 AND t.order_item = 1;";

            int i = statement.executeUpdate(sql);
            if(i > 0){
                System.out.println("更改成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection,statement, null);
        }
    }
}

```

#### 4 - Read 查询

```java
package top.calvinhaynes.CRUD;

import top.calvinhaynes.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ProjectName: JdbcTestQuery
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 21:02
 * @Description:利用JdbcUtils工具类进行测试增删改查中的查
 */
public class JdbcTestRead {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
//        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM orderitems WHERE order_num=20009;";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.println("order_item=" + resultSet.getInt("order_item"));
                System.out.println("prod_id=" + resultSet.getString("prod_id"));
                System.out.println("quantity=" + resultSet.getString("quantity"));
                System.out.println("item_price=" + resultSet.getString("item_price"));
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

