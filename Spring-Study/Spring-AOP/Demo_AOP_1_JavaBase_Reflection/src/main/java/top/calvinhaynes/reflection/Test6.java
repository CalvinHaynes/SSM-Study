package top.calvinhaynes.reflection;

/**
 * @ProjectName: Test6
 * @Author: CalvinHaynes
 * @Date: 2021/8/14 9:05
 * @Description:测试类加载器
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

        //获得系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));

        //类只能在以下这些地方被加载
        /*
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\charsets.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\deploy.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\access-bridge-64.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\cldrdata.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\dnsns.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\jaccess.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\jfxrt.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\localedata.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\nashorn.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\sunec.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\sunjce_provider.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\sunmscapi.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\sunpkcs11.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\ext\zipfs.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\javaws.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\jce.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\jfr.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\jfxswt.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\jsse.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\management-agent.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\plugin.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\resources.jar;
            D:\Software\JAVA\jdk1.8.0_181\jre\lib\rt.jar;
            D:\OpenSource\My Repository\My Repository for Github\Spring-Study\Spring-AOP\Demo_AOP_1_JavaBase_Reflection\target\classes;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-webmvc\5.3.9\spring-webmvc-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-aop\5.3.9\spring-aop-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-beans\5.3.9\spring-beans-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-context\5.3.9\spring-context-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-core\5.3.9\spring-core-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-jcl\5.3.9\spring-jcl-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-expression\5.3.9\spring-expression-5.3.9.jar;
            C:\Users\Administrator\.m2\repository\org\springframework\spring-web\5.3.9\spring-web-5.3.9.jar;
            D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\lib\idea_rt.jar
         */
    }
}
