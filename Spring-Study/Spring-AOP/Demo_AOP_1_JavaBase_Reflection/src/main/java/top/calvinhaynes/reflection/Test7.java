package top.calvinhaynes.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ProjectName: Test7
 * @Author: CalvinHaynes
 * @Date: 2021/8/14 9:19
 * @Description:获取类的信息
 */
public class Test7 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("top.calvinhaynes.reflection.User");

//        User user = new User();
//        c1 = user.getClass();

        //获取类的名字
        System.out.println(c1.getName()); //包名 + 类名

        //获得类的简单名字
        System.out.println(c1.getSimpleName()); //类名

        //获取类的属性
        System.out.println("=================================");
        Field[] fields = c1.getFields();    //只能找到public属性

        fields = c1.getDeclaredFields();    //能找到全部属性
        for (Field field : fields) {
            System.out.println(field);
        }

        //获得指定属性的值
        Field name = c1.getDeclaredField("name");
        System.out.println(name);

        //获得类的方法
        System.out.println("=================================");

        Method[] methods = c1.getMethods();     //获得本类和父类的全部[ublic方法
        for (Method method : methods) {
            System.out.println("getMethods:" + methods);
        }

        Method[] declaredMethods = c1.getDeclaredMethods();     //获得本类的所有方法
        for (Method declaredMethod : declaredMethods) {
            System.out.println("getDeclaredMethods:" + declaredMethod);
        }

        //获得指定方法
        //由于重载的存在,所以获得方法值的时候一定需要方法参数类型作为参数
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        //获得构造器
        System.out.println("=================================");

        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        constructors = c1.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        //获得指定构造器
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("获得指定构造器:" + declaredConstructor);

    }
}
