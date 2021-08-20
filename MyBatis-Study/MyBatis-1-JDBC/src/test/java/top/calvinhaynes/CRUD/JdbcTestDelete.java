package top.calvinhaynes.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import top.calvinhaynes.utils.JdbcUtils;


/**
 * jdbc���� ɾ��
 *
 * @author CalvinHaynes
 * @date 2021/08/20
 */
public class JdbcTestDelete {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement  statement  = null;

//      ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement  = connection.createStatement();

            String sql = "DELETE FROM exam1.orderitems WHERE order_num = 20009 AND order_item = 8";
            int    i   = statement.executeUpdate(sql);

            if (i > 0) {
                System.out.println("ɾ���ɹ���");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, null);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
