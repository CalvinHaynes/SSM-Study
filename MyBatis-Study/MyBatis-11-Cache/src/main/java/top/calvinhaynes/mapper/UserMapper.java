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

    /**
     * 按ID查询User
     *
     * @param id id
     * @return {@link Users}
     */
    Users selectUserById(int id);

    /**
     * Update user by name int.
     * 通过用户名修改用户
     *
     * @param user the user
     * @return the int
     */
    int updateUserByName(Users user);

}
