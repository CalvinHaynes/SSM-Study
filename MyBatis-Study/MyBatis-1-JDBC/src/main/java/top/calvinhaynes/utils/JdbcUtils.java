package top.calvinhaynes.utils;

import java.io.IOException;
import java.io.InputStream;

import java.sql.*;

import java.util.Properties;


/**
 * Jdbc utils.
 *
 * @author CalvinHaynes
 * @date 2021/08/21
 */
public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
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
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            // 驱动只加载一次
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close.
     * 释放资源的静态工具方法
     *
     * @param connection the connection
     * @param statement  the statement
     * @param resultSet  the result set
     * @throws SQLException the sql exception
     */
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

    /**
     * Gets connection.
     * 获取连接的静态工具方法
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

