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

            // getResourceAsStream:�������ڶ�ȡָ����Դ��������
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");

            // ����һ��û��Ĭ��ֵ�Ŀ������б�.
            Properties properties = new Properties();

            // ���� inputStream �Ƿ�Ϊ�գ����Ϊ�����쳣 AssertionError
            assert inputStream != null;

            // ���� db.properties �е������б�
            properties.load(inputStream);

            // �ڴ������б�����������ָ���������ԡ�
            // ����ڴ������б����Ҳ����ü�����ݹ�ؼ��Ĭ�������б���Ĭ��ֵ��
            // ���δ�ҵ������ԣ��÷���������null
            driver   = properties.getProperty("driver");
            url      = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            // ����ֻ����һ��
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // �ͷ���Դ�ľ�̬���߷���
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

    // ��ȡ���ӵľ�̬���߷���
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
