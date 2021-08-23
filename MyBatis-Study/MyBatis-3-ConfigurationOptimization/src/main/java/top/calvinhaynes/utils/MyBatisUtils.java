package top.calvinhaynes.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * MyBatis 自定义工具类
 *
 * @author CalvinHaynes
 * @date 2021/08/19
 */
public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //从 XML 中构建 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从SqlSessionFactory 中获取 SqlSession
     *
     * @return the sql session
     */
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

}
