package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import top.calvinhaynes.pojo.Users;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.List;


public class Test1 {

    static Logger logger = Logger.getLogger(Test1.class);

    @Test
    public void selectUserByIdTest() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        logger.info("测试Info");
        System.out.println(user);

        session.close();
    }

    @Test
    public void testLog4j(){

        logger.info("info: 测试log4j");
        logger.debug("debug: 测试log4j");
        logger.error("error:测试log4j");

    }
}
