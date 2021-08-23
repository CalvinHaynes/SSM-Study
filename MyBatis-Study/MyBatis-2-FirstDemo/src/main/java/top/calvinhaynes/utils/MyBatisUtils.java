package top.calvinhaynes.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis自定义工具类
 * The type My batis utils.
 *
 * @author CalvinHaynes
 * @date 2021/08/23
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
     * Get session sql session.
     *
     * @return the sql session
     */
//从SqlSessionFactory 中获取 SqlSession
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

}
