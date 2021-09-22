package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.User;


/**
 * UserMapper接口(CRUD）
 *
 * @author CalvinHaynes
 * @date 2021/09/22
 */
public interface UserMapper {
    /**
     * 按ID查询User
     *
     * @param i 我
     * @return {@link User}
     */
    User selectUserById(int i);

}
