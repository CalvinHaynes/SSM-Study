package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Users;

import java.util.List;
import java.util.Map;


/**
 * 用户映射器
 *
 * @author CalvinHaynes
 * @date 2021/08/19
 */
public interface UserMapper {
    /**
     * Select users by limit
     *
     * @param map the map
     * @return the list
     */
    List<Users> selectUsersByLimit(Map<String, Integer> map);

    /**
     * Select users by row bounds .
     *
     * @return the list
     */
    List<Users> selectUsersByRowBounds();


}
