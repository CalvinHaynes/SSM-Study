package top.calvinhaynes.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ProjectName: MyBatisUtils
 * @Author: CalvinHaynes
 * @Date: 2021/8/17 0:53
 * @Description:MyBatis工具类
 */
public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //从 XML 中构建 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream= Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从SqlSessionFactory 中获取 SqlSession
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }

}
