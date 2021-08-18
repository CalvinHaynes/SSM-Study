package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.User;
import top.calvinhaynes.utils.MyBatisUtils;

public class ResultMapTest {
    @Test
    public void selectUserByIdTest(){
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUserById(1002);
        System.out.println(user);

        session.close();
    }
}
