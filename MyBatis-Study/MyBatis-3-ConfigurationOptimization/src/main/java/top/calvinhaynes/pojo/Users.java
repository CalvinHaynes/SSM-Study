package top.calvinhaynes.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @ProjectName: User
 * @Author: CalvinHaynes
 * @Date: 2021/8/17 0:59
 * @Description:与数据库数据相关的实体类
 */
@Alias("user")
public class Users {
    private int id;
    private String name;
    private String pwd;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + pwd + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.pwd = password;
    }

    public Users() {
    }
}
