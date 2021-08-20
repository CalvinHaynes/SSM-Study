package top.calvinhaynes;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

/**
 * jdbc的第一个测试
 *
 * @author CalvinHaynes
 * @date 2021/08/20
 */
public class JdbcFirstTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1.加载驱动（固定写法）
        // DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.用户信息和 URL
        // URL = "协议：//主机地址：端口号/数据库名?参数1&参数2&参数3"
        String url = "jdbc:mysql://localhost:3306/exam1?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String userName = "root";
        String password = "412523chx";

        // 3.连接成功,返回数据库对象 connection
        Connection connection = DriverManager.getConnection(url, userName, password);

        // 4.创建执行SQL的对象 statement
        Statement statement = connection.createStatement();

        // 5.执行SQL的对象去执行SQL,如果有本程序需要用的数据操作，再在本此处进行操作
        // 要执行的SQL语句
        String sql = "SELECT * FROM customers";

        // 执行SQL语句,拿到返回结果
        ResultSet resultSet = statement.executeQuery(sql);

        // 打印一下返回结果看看效果
        while (resultSet.next()) {
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

        // 6.关闭数据库连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

