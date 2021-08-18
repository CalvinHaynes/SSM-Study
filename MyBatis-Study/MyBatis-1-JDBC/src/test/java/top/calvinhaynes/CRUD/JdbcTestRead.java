package top.calvinhaynes.CRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import top.calvinhaynes.utils.JdbcUtils;

/**
 * @ProjectName: JdbcTestQuery
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 21:02
 * @Description:利用JdbcUtils工具类进行测试增删改查中的查
 */
public class JdbcTestRead {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement  statement  = null;

//      ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement  = connection.createStatement();

            String    sql       = "SELECT * FROM orderitems WHERE order_num=20009;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("order_item=" + resultSet.getInt("order_item"));
                System.out.println("prod_id=" + resultSet.getString("prod_id"));
                System.out.println("quantity=" + resultSet.getString("quantity"));
                System.out.println("item_price=" + resultSet.getString("item_price"));
                System.out.println("============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, null);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
