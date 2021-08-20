package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.List;

/**
 * 所有测试
 *
 * @author CalvinHaynes
 * @date 2021/08/19
 */
public class AllTest {

    /**
     * 查询所有用户测试
     */
    @Test
    public void selectUserTest(){

        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Users> users = mapper.getAllUsers();

        for (Users user : users) {
            System.out.println(user);
        }

        session.close();
    }

    /**
     * 通过用户id查询用户信息测试
     */
    @Test
    public void selectUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        session.close();
    }

}
