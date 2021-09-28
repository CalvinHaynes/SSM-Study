package top.calvinhaynes.pojo;


/**
 * 用户实体类
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
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
