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
 * @date 2021 /08/19
 */
public class AllTest {

    /**
     * 查询所有用户测试
     */
    @Test
    public void selectUserTest() {

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

    /**
     * Add user test.
     */
    @Test
    public void addUserTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int user = mapper.addUser(new Users(1006, "James", "89098766"));

        if (user > 0) {
            System.out.println("添加成功");
        }

        session.close();
    }

    /**
     * Delete user by id test.
     */
    @Test
    public void deleteUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int user = mapper.deleteUserById(1006);

        if (user > 0) {
            System.out.println("删除成功");
        }

        session.close();
    }

    /**
     * Update user by name test.
     */
    @Test
    public void updateUserByNameTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int user = mapper.updateUserByName(new Users(1007, "LiHua", "8905467577"));

        if (user > 0) {
            System.out.println("更新成功");
        }

        session.close();
    }


}
