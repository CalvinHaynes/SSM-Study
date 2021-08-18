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
    private static String driver   = null;
    private static String url      = null;
    private static String username = null;
    private static String password = null;

    static {
        try {

            // getResourceAsStream:返回用于读取指定资源的输入流
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");

            // 创建一个没有默认值的空属性列表.
            Properties properties = new Properties();

            // 断言 inputStream 是否为空，如果为空抛异常 AssertionError
            assert inputStream != null;

            // 加载 db.properties 中的属性列表
            properties.load(inputStream);

            // 在此属性列表中搜索具有指定键的属性。
            // 如果在此属性列表中找不到该键，则递归地检查默认属性列表及其默认值。
            // 如果未找到该属性，该方法将返回null
            driver   = properties.getProperty("driver");
            url      = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            // 驱动只加载一次
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 释放资源的静态工具方法
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    // 获取连接的静态工具方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
