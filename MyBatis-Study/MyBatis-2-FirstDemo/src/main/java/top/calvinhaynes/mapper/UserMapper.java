package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;
import java.util.Map;


/**
 * The interface User mapper.
 */
public interface UserMapper {
    /**
     * Select user list.
     * 查询所有User
     *
     * @return the list
     */

    List<Users> selectUser();

    /**
     * Select user by id users.
     * 按ID查询User
     *
     * @param i the
     * @return the users
     */
    Users selectUserById(int i);

    /**
     * Update user by name int.
     * 通过用户名修改用户
     *
     * @param user the user
     * @return the int
     */
    int updateUserByName(Users user);

    /**
     * Add user int.
     * /增加用户
     *
     * @param user the user
     * @return the int
     */
    int addUser(Users user);

    /**
     * Delete user by id int.
     * 通过id删除一个用户
     *
     * @param id the id
     * @return the int
     */

    int deleteUserById(int id);

    /**
     * Add user by map int.
     * 利用Map添加一个用户
     *
     * @param map the map
     * @return the int
     */

    int addUserByMap(Map map);

    /**
     * Select user like list.
     * 根据姓名关键字模糊查询
     *
     * @param value the value
     * @return the list
     */

    List<Users> selectUserLike(String value);


}
