package top.calvinhaynes.CRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import top.calvinhaynes.utils.JdbcUtils;

/**
 * @ProjectName: JdbcTest2
 * @Author: CalvinHaynes
 * @Date: 2021/8/16 20:54
 * @Description:利用JdbcUtils工具类进行测试增删改查中的增
 */
public class JdbcTestCreate {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement  statement  = null;

//      ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement  = connection.createStatement();

            String sql = "INSERT INTO orderitems(order_num, order_item, prod_id, quantity, item_price)\n"
                         + "VALUES (20009,8,'FB',20,23.99);";
            int i = statement.executeUpdate(sql);

            if (i > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, null);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
