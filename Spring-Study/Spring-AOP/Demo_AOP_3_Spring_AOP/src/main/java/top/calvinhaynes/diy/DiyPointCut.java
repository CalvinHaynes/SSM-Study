package top.calvinhaynes.diy;

/**
 * @ProjectName: DiyPointCut
 * @Author: CalvinHaynes
 * @Date: 2021/8/15 10:07
 * @Description:自定义切面类
 */
public class DiyPointCut {

    public void beforeLog(){
        System.out.println("========执行前========");
    }

    public void afterLog() {
        System.out.println("========执行后========");
    }
}
