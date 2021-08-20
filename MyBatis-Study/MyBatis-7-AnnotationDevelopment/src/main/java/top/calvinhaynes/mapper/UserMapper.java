package top.calvinhaynes.mapper;

import org.apache.ibatis.annotations.*;
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
     * 查询所有User
     *
     * @return {@link List}<{@link Users}>
     */
    @Select("select * from users")
    List<Users> getAllUsers();

    /**
     * 按ID查询User
     *
     * @param id id
     * @return {@link Users}
     */
    @Select("select * from users where id = #{id}")
    Users selectUserById(@Param("id") int id);

    /**
     * Add user
     *
     * @param user the user
     * @return the int
     */
    @Insert("insert into users (id,name,password) values (#{id},#{name},#{password})")
    int addUser(Users user);


    /**
     * Delete user by id
     *
     * @param id the id
     * @return the int
     */
    @Delete("delete from users where id = #{uid}")
    int deleteUserById(@Param("uid") int id);


    /**
     * Update user by name
     *
     * @param user the user
     * @return the int
     */
    @Update("update users set id = #{id},password = #{password} where name = #{name}")
        int updateUserByName(Users user);
        }
