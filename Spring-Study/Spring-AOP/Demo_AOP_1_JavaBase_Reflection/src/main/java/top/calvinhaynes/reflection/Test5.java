package top.calvinhaynes.reflection;

/**
 * @ProjectName: Test5
 * @Author: CalvinHaynes
 * @Date: 2021/8/14 8:33
 * @Description:类初始化发生场景的测试和分析
 */
public class Test5 {

    static {
        System.out.println("Main类被初始化");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //类的主动引用测试（一定出现类的初始化）
        //1.new 一个类的对象
//        Son son = new Son();

        //2.使用反射调用
//        Class sonClass = Class.forName("top.calvinhaynes.reflection.Son");

        //3.调用类的静态成员(除了final常量)和静态方法
//        System.out.println(Son.id);
//        Son.show();


        //类的被动引用(不会发生类的初始化)
        //1.通过子类引用父类的静态变量
//        System.out.println(Son.age);

        //2.通过数组定义类引用
//        Son[] sons = new Son[10];

        //3.引用常量 final(常量在链接阶段就已经存入调用类的常量池中了)
//        System.out.println(Son.ID);

    }

}

class Father{
    static int age = 40;

    static{
        System.out.println("父类被初始化");
    }
}

class Son extends Father {
    static {
        System.out.println("子类被初始化");
        id = 2002;
    }

    public static void show(){
        System.out.println("子类的静态方法调用");
    }

    static int id = 100;
    static final int ID = 1;
}