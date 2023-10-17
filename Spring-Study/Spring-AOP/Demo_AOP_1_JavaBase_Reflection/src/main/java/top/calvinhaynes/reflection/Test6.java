package top.calvinhaynes.reflection;


/**
 * test6：测试类加载器
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
public class Test6 {
    public static void main(String[] args) throws ClassNotFoundException {

        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取系统类加载器的父类加载器 --> 扩展类加载器
        ClassLoader parentClassLoader = systemClassLoader.getParent();
        System.out.println(parentClassLoader);

        //获取扩展类加载器的父类加载器 --> 根加载器/引导类加载器(C/C++),获取不到
        ClassLoader grandParentClassLoader = parentClassLoader.getParent();
        System.out.println(grandParentClassLoader);

        //测试当前类是由哪个加载器加载的
        ClassLoader classLoader = Class.forName("top.calvinhaynes.reflection.Test6").getClassLoader();
        System.out.println(classLoader);

        //测试JDK内置类是谁加载的
        ClassLoader objectClassLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(objectClassLoader);

        //获得系统类加载器可以加载的路径,类只能在以下这些地方被加载
        System.out.println(System.getProperty("java.class.path"));

    }
}
