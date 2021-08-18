package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.User;

/**
 * @ProjectName: UserMapper
 * @Author: CalvinHaynes
 * @Date: 2021/8/18 12:56
 * @Description:UserMapper接口(CRUD）
 */
public interface UserMapper {
    //按ID查询User
    User selectUserById(int i);

}
