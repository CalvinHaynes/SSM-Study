package top.calvinhaynes.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import top.calvinhaynes.pojo.Users;

import java.util.List;

/**
 * 用户映射器impl
 * The type User mapper.
 * 方式一所用实现类
 *
 * @author CalvinHaynes
 * @date 2021 /08/23
 */
public class UserMapperImpl implements UserMapper{


    /**
     * 我们的所有操作，在原来都使用sqlSession来执行，现在都使用SqlsessionTemplate
     * 单例模式 私有化
     * */
    private SqlSessionTemplate sqlSession;

    /**
     * Sets sql session.
     *
     * @param sqlSession the sql session
     */
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 得到所有用户
     *
     * @return {@link List}<{@link Users}>
     */
    @Override
    public List<Users> getAllUsers() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getAllUsers();
    }

}
