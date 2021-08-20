package top.calvinhaynes.pojo;

import org.apache.ibatis.type.Alias;

/**
 * 用户
 * The type Users.
 *
 * @author CalvinHaynes
 * @date 2021/08/20
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return pwd;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.pwd = password;
    }

    /**
     * Instantiates a new Users.
     *
     * @param id       the id
     * @param name     the name
     * @param password the password
     */
    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.pwd = password;
    }

    /**
     * Instantiates a new Users.
     */
    public Users() {
    }
}
