package top.calvinhaynes.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ProjectName: BeforeLog
 * @Author: CalvinHaynes
 * @Date: 2021/8/15 0:53
 * @Description:前置日志增强类
 */
public class BeforeLog implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "被执行了");
    }
}
