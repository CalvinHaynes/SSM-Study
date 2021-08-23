package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;


/**
 * 用户映射器
 *
 * @author CalvinHaynes
 * @date 2021 /08/19
 */
public interface UserMapper {
    /**
     * Add user int.
     *
     * @param user the user
     * @return the int
     */
    int addUser(Users user);

    /**
     * 通过ID删除用户
     * Delete user int.
     *
     * @param id id
     * @return the int
     */
    int deleteUserById(int id);

    /**
     * Comprehensive user list.
     *
     * @return the list
     */
    List<Users> comprehensiveUser();

}
