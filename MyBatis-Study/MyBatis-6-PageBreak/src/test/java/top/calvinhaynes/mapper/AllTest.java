package top.calvinhaynes.mapper;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 所有测试
 *
 * @author CalvinHaynes
 * @date 2021/08/19
 */
public class AllTest {

    /**
     * 分页查询用户测试 LIMIT
     */
    @Test
    public void selectUserByLimitTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex", 0);
        map.put("pageSize", 2);

        List<Users> users = mapper.selectUsersByLimit(map);

        for (Users user : users) {
            System.out.println(user);
        }

        session.close();
    }


    /**
     * 使用RowBounds类实现分页查询测试
     */
    @Test
    public void selectUserByRowBoundsTest() {
        SqlSession session = MyBatisUtils.getSession();

        RowBounds rowBounds = new RowBounds(0, 2);
        List<Users> usersList = session.selectList("top.calvinhaynes.mapper.UserMapper.selectUsersByRowBounds", null, rowBounds);

        for (Users users : usersList) {
            System.out.println(users);
        }
        session.close();
    }
}
