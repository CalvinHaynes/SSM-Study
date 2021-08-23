package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

/**
 * 所有测试
 *
 * @author CalvinHaynes
 * @date 2021/08/19
 */
public class AllTest {

    /**
     * 通过用户id查询用户信息测试,在同一个session中同时查询两次，看缓存效果
     */
    @Test
    public void Level1CacheTest1() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        Users user2 = mapper.selectUserById(1002);
        System.out.println(user2);

        session.close();
    }

    @Test
    public void Level1CacheTest2() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        System.out.println("================================");

        int mary = mapper.updateUserByName(new Users(1008, "Mary", "5558955"));
        if (mary > 0) {
            System.out.println("更新成功");
        }

        session.close();
    }

    @Test
    public void Level1CacheTest3() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        //手动清理缓存
        session.clearCache();

        Users user2 = mapper.selectUserById(1002);
        System.out.println(user2);

        session.close();
    }

    @Test
    public void Level2CacheTest1() {
        SqlSession session = MyBatisUtils.getSession();
        SqlSession session2 = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        UserMapper mapper2 = session2.getMapper(UserMapper.class);


        Users user = mapper.selectUserById(1002);
        System.out.println(user);
        session.close();

        Users user2 = mapper2.selectUserById(1002);
        System.out.println(user2);

        session2.close();
    }

}
