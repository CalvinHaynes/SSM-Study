package top.calvinhaynes.mapper;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.pojo.Users;

import java.util.List;

/**
 * 所有的测试
 *
 * @author CalvinHaynes
 * @date 2021 /08/23
 */
public class AllTest {

    /**
     * test1:测试未使用事务时的情况，此时设置delete语句是错误的，
     * 结果在执行复杂的事务时，先增加一个用户再删除一个用户的操作下，
     * 本应该两者同时成功和同时失败，但是未启用Spring声明式事务时，
     * 由于添加用户的操作是无误的，所以正常执行，而删除操作由于语句有错，所以
     * 未执行，这显然导致数据不安全，而且不满足事务的原子性
     */
    @Test
    public void test1() {
        System.out.println("===========测试一：未使用声明式事务==========");

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserMapper mapper = context.getBean("userMapper", UserMapper.class);

        List<Users> usersList = mapper.comprehensiveUser();

        for (Users users : usersList) {
            System.out.println(users);
        }
    }


    /**
     * test2:测试使用了Spring声明式事务之后的变化，虽然和上一个测试方法是一模一样的，但是使用了声明式事务之后，
     * 符合事务操作的原子性，同时成功同时失败的原则
     * 保证了数据的安全性
     */
    @Test
    public void test2() {
        System.out.println("===========测试二：使用声明式事务==========");

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext2.xml");

        UserMapper mapper = context.getBean("userMapper", UserMapper.class);

        List<Users> usersList = mapper.comprehensiveUser();

        for (Users users : usersList) {
            System.out.println(users);
        }
    }
}
