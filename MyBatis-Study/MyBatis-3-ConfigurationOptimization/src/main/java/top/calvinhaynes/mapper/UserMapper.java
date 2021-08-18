package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;

/**
 * @ProjectName: UserMapper
 * @Author: CalvinHaynes
 * @Date: 2021/8/18 12:56
 * @Description:UserMapper接口(CRUD）
 */
public interface UserMapper {
    //查询所有User
    List<Users> selectUser();

    //按ID查询User
    Users selectUserById(int i);

    //通过用户名修改用户
    int updateUserByName(Users user);

    //增加用户
    int addUser(Users user);

    //通过id删除一个用户
    int deleteUserById(int id);

}