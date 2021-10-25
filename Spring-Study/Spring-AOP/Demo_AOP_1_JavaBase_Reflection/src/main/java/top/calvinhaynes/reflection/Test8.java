package top.calvinhaynes.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * test8:动态的创建一个对象，利用反射
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
public class Test8 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获得Class对象
        Class userClass = Class.forName("top.calvinhaynes.reflection.User");

        //构造一个对象
//        User user = (User) userClass.newInstance(); //本质上是调用了类的无参构造器
//        System.out.println(user);

        //通过构造器创建对象
//        Constructor constructor = userClass.getDeclaredConstructor(String.class, int.class, int.class);
//        User user2 = (User) constructor.newInstance("Calvin Haynes", 20020412, 19);
//        System.out.println(user2);

        //通过反射调用普通方法
        User user3 = (User) userClass.newInstance();
        //反射获取方法
        Method setName = userClass.getMethod("setName", String.class);

        //反射调用方法 invoke(调用方法的对象，方法的参数值)
        setName.invoke(user3, "Calvin Haynes");
        System.out.println("通过反射调用方法:" + user3.getName());

        //通过反射操作属性
        User user4 = (User) userClass.newInstance();
        //获取属性
        Field name = userClass.getDeclaredField("name");

        //禁用访问安全检查：name属性是private的
        //同时禁用访问安全检查还会提高反射效率，下一个测试例子中详解
        name.setAccessible(true);
        //设置属性的值
        name.set(user4, "Jack London");
        System.out.println("通过反射操作属性:" + user4.getName());


    }

}
