package top.calvinhaynes.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 用户实体类
 * The type Users.
 *
 * @author CalvinHaynes
 * @date 2021/08/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("user")
public class Users {
    private int id;
    private String name;
    private String password;
    private String lastName;

}
