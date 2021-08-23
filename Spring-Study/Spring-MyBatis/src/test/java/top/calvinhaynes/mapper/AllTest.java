package top.calvinhaynes.mapper;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.pojo.Users;

/**
 * 所有的测试
 *
 * @author CalvinHaynes
 * @date 2021 /08/23
 */
public class AllTest {

    /**
     * 测试第一种整合方法 sqlSessionTemplate
     */
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);

        for (Users user : userMapper.getAllUsers()) {
            System.out.println(user);
        }
    }

    /**
     * 测试第二种整合方法 SqlSessionDaoSupport
     */
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserMapper userMapper = context.getBean("userMapper2", UserMapper.class);

        for (Users user : userMapper.getAllUsers()) {
            System.out.println(user);
        }
    }
}
