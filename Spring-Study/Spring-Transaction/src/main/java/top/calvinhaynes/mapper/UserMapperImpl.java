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
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public int addUser(Users user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUserById(id);
    }

    @Override
    public List<Users> comprehensiveUser() {
        Users luise = new Users(1010, "Luise", "5985625","Anna");
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);

        mapper.addUser(luise);
        mapper.deleteUserById(1005);

        return mapper.comprehensiveUser();
    }

}
