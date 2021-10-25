package top.calvinhaynes.annotation;


/**
 * 测试内置注解
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
@SuppressWarnings("all")//此类所有警告全删
public class MyTest01 {

    //@Override 重写注解 检查约束重写方法名,不对报错
    //@Override
    //public String tring() {
    //    return super.toString() + "haa";
    //}

    //过时的注解,不推荐使用,但是可以使用,存在更好的方式
    @Deprecated
    public static void test(){
        System.out.println("Deprecated");
    }

    public static void main(String[] args) {
       test();
    }

}
