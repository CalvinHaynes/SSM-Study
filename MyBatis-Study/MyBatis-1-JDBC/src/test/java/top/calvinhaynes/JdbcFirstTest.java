package top.calvinhaynes;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

/**
 * jdbc�ĵ�һ������
 *
 * @author CalvinHaynes
 * @date 2021/08/20
 */
public class JdbcFirstTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1.�����������̶�д����
        // DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.�û���Ϣ�� URL
        // URL = "Э�飺//������ַ���˿ں�/���ݿ���?����1&����2&����3"
        String url = "jdbc:mysql://localhost:3306/exam1?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String userName = "root";
        String password = "412523chx";

        // 3.���ӳɹ�,�������ݿ���� connection
        Connection connection = DriverManager.getConnection(url, userName, password);

        // 4.����ִ��SQL�Ķ��� statement
        Statement statement = connection.createStatement();

        // 5.ִ��SQL�Ķ���ȥִ��SQL,����б�������Ҫ�õ����ݲ��������ڱ��˴����в���
        // Ҫִ�е�SQL���
        String sql = "SELECT * FROM customers";

        // ִ��SQL���,�õ����ؽ��
        ResultSet resultSet = statement.executeQuery(sql);

        // ��ӡһ�·��ؽ������Ч��
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

        // 6.�ر����ݿ�����
        resultSet.close();
        statement.close();
        connection.close();
    }
}

