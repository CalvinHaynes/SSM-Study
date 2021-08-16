package top.calvinhaynes.reflection;

/**
 * @ProjectName: Test1
 * @Author: CalvinHaynes
 * @Date: 2021/8/10 0:45
 * @Description:反射获取对象
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException {
        //反射获取类的Class对象
        Class user = Class.forName("top.calvinhaynes.reflection.User");
        System.out.println(user);   //class top.calvinhaynes.reflection.User

        Class user1 = Class.forName("top.calvinhaynes.reflection.User");
        Class user2 = Class.forName("top.calvinhaynes.reflection.User");
        Class user3 = Class.forName("top.calvinhaynes.reflection.User");

        //一个类在内存中只有一个Class对象
        //一个类被加载后,类的整个结构都被封装在Class对象中
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user3.hashCode());

    }
}

class User {
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    private void test() {

    }
}

