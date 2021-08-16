package top.calvinhaynes.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @ProjectName: AfterLog
 * @Author: CalvinHaynes
 * @Date: 2021/8/15 0:53
 * @Description:后置日志增强类
 */
public class AfterLog implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了" + method.getName() + "方法，返回结果为" + returnValue);
    }
}
