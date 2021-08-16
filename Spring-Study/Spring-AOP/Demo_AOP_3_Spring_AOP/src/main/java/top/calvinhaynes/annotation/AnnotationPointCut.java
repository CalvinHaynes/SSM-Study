package top.calvinhaynes.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @ProjectName: AnnotationPointCut
 * @Author: CalvinHaynes
 * @Date: 2021/8/15 9:08
 * @Description:使用注解实现AOP，注解切面类
 */
@Aspect //表面此类是一个切面
public class AnnotationPointCut {

    @Before("execution(* top.calvinhaynes.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("=========方法执行前=========");
    }

    @After("execution(* top.calvinhaynes.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("=========方法执行后=========");
    }

    //在环绕增强中,可以给定一个参数，代表我们要获取处理切入的点
    /*

    Joint Point：
        - JointPoint是程序运行过程中可识别的点，这个点可以用来作为AOP切入点。
        - JointPoint对象则包含了和切入相关的很多信息。
        - 比如切入点的对象，方法，属性等。
        - 我们可以通过反射的方式获取这些点的状态和信息，用于追踪tracing和记录logging应用信息。

    ProceedingJoinPoint：
        - Proceedingjoinpoint 继承了 JoinPoint。
        - 是在JoinPoint的基础上暴露出 proceed 这个方法。
        - proceed很重要，这个是aop代理链执行的方法。
        - 环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的
        - ProceedingJoinPoint is only supported for around advice

     */
    @Around("execution(* top.calvinhaynes.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=========环绕前=========");

        //获得 signature
        Signature signature = joinPoint.getSignature();
        System.out.println(signature);

        //执行方法，返回方法调用后的返回值
        Object proceed = joinPoint.proceed();

        System.out.println(proceed);

        System.out.println("=========环绕后=========");
    }
}
