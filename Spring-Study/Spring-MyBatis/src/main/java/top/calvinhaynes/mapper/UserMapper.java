package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;


/**
 * 用户映射器
 *
 * @author CalvinHaynes
 * @date 2021/08/19
 */
public interface UserMapper {
    /**
     * 查询所有User
     *
     * @return {@link List}<{@link Users}>
     */
    List<Users> getAllUsers();

}
