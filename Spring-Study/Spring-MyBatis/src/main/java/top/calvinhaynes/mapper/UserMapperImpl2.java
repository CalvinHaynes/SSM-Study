package top.calvinhaynes.mapper;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import top.calvinhaynes.pojo.Users;

import java.util.List;

/**
 * 用户映射器impl2
 * The type User mapper impl 2.
 * 方式二所用实现类
 *
 * @author CalvinHaynes
 * @date 2021/08/23
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<Users> getAllUsers() {
        return getSqlSession().getMapper(UserMapper.class).getAllUsers();
    }
}
