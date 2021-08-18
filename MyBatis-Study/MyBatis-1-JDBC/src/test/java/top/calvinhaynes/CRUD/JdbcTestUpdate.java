package top.calvinhaynes.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import top.calvinhaynes.utils.JdbcUtils;

/**
 * @ProjectName: JdbcTestUpdate
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 21:14
 * @Description:利用JdbcUtils工具类进行测试增删改查中的改
 */
public class JdbcTestUpdate {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement  statement  = null;

//      ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement  = connection.createStatement();

            String sql =
                "UPDATE exam1.orderitems t SET t.item_price = 5.50 WHERE t.order_num = 20008 AND t.order_item = 1;";
            int i = statement.executeUpdate(sql);

            if (i > 0) {
                System.out.println("更改成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, null);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
