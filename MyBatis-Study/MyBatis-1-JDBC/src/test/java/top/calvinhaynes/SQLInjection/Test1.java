package top.calvinhaynes.SQLInjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import top.calvinhaynes.utils.JdbcUtils;

/**
 * @ProjectName: Test1
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 23:02
 * @Description:SQLע�������
 */
public class Test1 {
    public static void logIn(String name, String password) throws SQLException {
        Connection connection = null;
        Statement  statement  = null;

//      ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement  = connection.createStatement();

            String    sql       = "SELECT * FROM users WHERE `NAME`='" + name + "' AND `PASSWORD`='" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("ʵ��ִ������ǣ�" + sql + "\n");

            while (resultSet.next()) {
                System.out.println("id:" + resultSet.getString("id"));
                System.out.println("Name:" + resultSet.getString("name"));
                System.out.println("Password:" + resultSet.getString("password"));
                System.out.println("============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, null);
        }
    }

    public static void main(String[] args) throws SQLException {

        // ����ƴ�Ӳ�ѯ
        logIn("' or '1=1", "' or '1=1");

        // �������
        // ʵ��ִ������ǣ�SELECT * FROM users WHERE `NAME`='' or '1=1' AND `PASSWORD`='' or '1=1'
        // ��������ƴ���ַ����󣬵��� WHERE ����������棬��Ȼ�ͻ�������е� users ��Ϣ
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
