package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.HashMap;
import java.util.List;


public class Test1 {
    @Test
    public void selectUserTest() {

        //先利用工具类拿到SqlSession
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Users> users = mapper.selectUser();

        for (Users user : users) {
            System.out.println(user);
        }

        //关闭SqlSession
        session.close();
    }

    @Test
    public void addUserByMapTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);


        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 1005);
        map.put("userName", "Calvin");
        map.put("userPassword", "888888");
        map.put("userLastName", "Durant");

        int i = mapper.addUserByMap(map);

        if (i > 0) {
            System.out.println("利用Map作为参数添加用户成功");
        }

        session.commit();
        session.close();
    }


    @Test
    public void selectUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        session.close();
    }

    @Test
    public void selectUserLikeTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        //方式一：需要在调用处手动的去添加"%"通配符
        //List<Users> usersList = mapper.selectUserLike("%i%");

        //方式二：配置文件已经添加了"%"通配符
        List<Users> usersList = mapper.selectUserLike("i");

        for (Users users : usersList) {
            System.out.println(users);
        }

        session.close();
    }

    @Test
    public void updateUserByNameTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users calvin = new Users(1003, "Calvin", "987654");
        int res = mapper.updateUserByName(calvin);

        if (res > 0) {
            System.out.println("更改用户" + calvin.getName() + "的信息成功");
        }

        //增删改一定要提交事务
        session.commit();
        session.close();
    }

    @Test
    public void addUserTest() {
        SqlSession session = MyBatisUtils.getSession();

        Users liHua = new Users(1004, "LiHua", "5418814");
        UserMapper mapper = session.getMapper(UserMapper.class);
        int res = mapper.addUser(liHua);

        if (res > 0) {
            System.out.println("添加用户" + liHua + "成功");
        }

        session.commit();
        session.close();
    }

    @Test
    public void deleteUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.deleteUserById(1004);

        if (i > 0) {
            System.out.println("删除用户成功");
        }

        session.commit();
        session.close();
    }
}
