package top.calvinhaynes.reflection;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;

/**
 * @ProjectName: Test3
 * @Author: CalvinHaynes
 * @Date: 2021/8/11 22:48
 * @Description:获取各种类型的反射对象
 */
public class Test3 {
    public static void main(String[] args) {

        Class c1 = Object.class;        //类
        Class c2 = Comparable.class;    //接口
        Class c3 = String[].class;      //一维数组
        Class c4 = int[][].class;       //二维数组
        Class c5 = Override.class;      //注解
        Class c6 = ElementType.class;   //枚举类
        Class c7 = Integer.class;       //基本类型包装类
        Class c8 = void.class;          //关键字
        Class c9 = Class.class;         //Class类

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);

        //只要类型相同,Class就是同一个
        int[] a = new int[100];
        int[] b = new int[1000];
        System.out.println(a.getClass().hashCode() == b.getClass().hashCode());


    }
}
