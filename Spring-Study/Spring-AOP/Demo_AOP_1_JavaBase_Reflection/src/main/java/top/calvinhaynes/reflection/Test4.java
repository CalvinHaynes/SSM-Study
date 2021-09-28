package top.calvinhaynes.reflection;


/**
 * test4:类加载过程的理解
 *
 * 过程详细解读
 *   1.加载：各类的class文件字节码加载到内存，将静态数据转换为方法区的动态数据结构，生成对应的Class对象
 *   2.链接：准备过程正式对类中的 static 变量设置默认初始值，本 demo 中就是 age ，此时 age = 0
 *   3.初始化：所有类变量的赋值操作和静态代码块中的语句按顺序合并执行
 *       <clint>(
 *           System.out.println("Cat类的静态代码块初始化");
 *           age = 10;
 *           age = 20;
 *       )
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
public class Test4 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println(Cat.age);
        System.out.println(cat.id);
    }
}


/**
 * 猫
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
class Cat{

    public int id = 10;
    //静态代码块：随着类的加载而执行，而且只执行一次
    static {
        System.out.println("Cat类的静态代码块初始化");
        age = 10;
    }

    static int age = 20;

    public Cat() {
        System.out.println("Cat类的无参构造器初始化");
    }
}
