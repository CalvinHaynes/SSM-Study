package top.calvinhaynes.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: Test9
 * @Author: CalvinHaynes
 * @Date: 2021/8/14 14:57
 * @Description:禁用安全监测提高反射效率的测试
 */
public class Test9 {

    //普通方式调用
    public static void test1(){

        User user = new User();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("普通方式调用方法执行10亿次用时：" + (endTime - startTime) + "ms");


    }

    //反射方式调用
    public static void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user1 = new User();
        Class userClass = user1.getClass();

        Method getName = userClass.getDeclaredMethod("getName", null);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user1,null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("反射方式调用方法执行10亿次用时：" + (endTime - startTime) + "ms");
    }

    //禁用安全检查调用
    public static void test3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user2 = new User();
        Class userClass = user2.getClass();

        Method getName = userClass.getDeclaredMethod("getName", null);
        getName.setAccessible(true);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user2, null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("禁用安全检查调用方法执行10亿次用时：" + (endTime - startTime) + "ms");
    }


    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test1();
        test2();
        test3();
    }

//    普通方式调用方法执行10亿次用时：6ms
//    反射方式调用方法执行10亿次用时：3210ms
//    反射方式调用方法执行10亿次用时：1666ms
}


