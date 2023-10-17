# Demo_AOP_3_Spring_AOP

## 前言

​		AOP是OOP的延续，是Aspect Oriented Programming的缩写，意思是面向切面编程。

​		可以通过预编译方式和运行期==动态代理==实现在不修改源代码的情况下给程序动态统一添加功能的一种技术。

​		AOP设计模式孜孜不倦追求的是调用者和被调用者之间的==解耦==，AOP 可以说也是这种目标的一种实现。

​		我们现在做的一些非业务，如：日志、事务、安全等都会写在业务代码中(也即是说，这些非业务类横切于业务类)，但这些代码往往是重复，复制——粘贴式的代码会给程序的维护带来不便，AOP 就实现了把这些业务需求与系统需求分开来做。这种解决的方式也称代理机制。

​		==AOP核心理解==：在不改变原有代码的情况下添加新的功能

## AOP中的一些概念

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等…
- 切面（ASPECT）：横切关注点被模块化的特殊对象。即，它是一个**类**。
- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个**方法**。
- 目标（Target）：被通知对象。
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 切入点（PointCut）：切面通知执行的“地点”的定义。
- 连接点（JointPoint）：与切入点匹配的执行点。

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.3v52rm1otlo0.png)

## Spring 实现AOP的方式（结合项目查看）

### 1 - 使用原生 Spring API 接口（实现Spring API接口）（此方式已过时，了解即可）

通过 implements 原生的 **Spring API** 接口来实现AOP

#### 1 - 下两例：实现了 AfterReturningAdvice 后置返回值通知接口和 MethodBeforeAdvice 方法前置通知接口

```java
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
```

```java
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
```

#### 2 - XML配置文件（applicationContext.xml）

==使用AOP之前一定要记得引入约束==

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"    
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
```

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl"/>
    <bean id="beforeLog" class="top.calvinhaynes.log.BeforeLog"/>
    <bean id="afterLog" class="top.calvinhaynes.log.AfterLog"/>

    <!--方式一：使用原生Spring API 接口-->
    <!--配置AOP 需要导入AOP的约束-->
    <aop:config>
        <!--切入点-->
        <aop:pointcut id="pointcut" expression="execution(* top.calvinhaynes.service.UserServiceImpl.*(..))"/>

        <!--执行环绕增加 -->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>

    </aop:config>
    
 </beans>
```

> 在使用AOP时，指定一个pointcut的同时会定义一个==expression==表达式，来表示对什么方法使用AOP。
>
> **execution**：一般**用于指定方法的执行，用的最多**。
>
> ```javascript
> execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? 
> name-pattern(param-pattern) throws-pattern?)
> ```
> - modifiers-pattern表示方法的访问类型，public等；
>
> - ret-type-pattern表示方法的返回值类型，如String表示返回类型是String，“*”表示所有的返回类型；
>
> - declaring-type-pattern表示方法的声明类，如“com.elim..*”表示com.elim包及其子包下面的所有类型；
>
> - name-pattern表示方法的名称，如“add*”表示所有以add开头的方法名；
>
> - param-pattern表示方法参数的类型，name-pattern(param-pattern)其实是一起的表示的方法集对应的参数类型，如“add()”表示不带参数的add方法，“add(*)”表示带一个任意类型的参数的add方法，“add(*,String)”则表示带两个参数，且第二个参数是String类型的add方法；
>
> - throws-pattern表示异常类型；
>
> ==其中以问号结束的部分都是可以省略的。==
>
> 上例中：=="execution(* top.calvinhaynes.service.UserServiceImpl.*(..))"==翻译过来就是指定对`top.calvinhaynes.service.UserServiceImpl`类下的所有方法执行AOP

#### 3 - 测试类和相关的POJO

```
public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //动态代理代理的是接口
        UserService userService = context.getBean("userService", UserService.class);

        userService.addUser();
    }
}
```

```java
/**
 * @ProjectName: UserService
 * @Author: CalvinHaynes
 * @Date: 2021/8/9 16:43
 * @Description:用户服务接口-增删改查业务
 */
public interface UserService {
    public void addUser();
    public void deleteUser();
    public void updateUser();
    public void queryUser();
}

```

```java
/**
 * @ProjectName: UserServiceImpl
 * @Author: CalvinHaynes
 * @Date: 2021/8/15 10:03
 * @Description:用户服务接口实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("增加一个用户");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除一个用户");
    }

    @Override
    public void updateUser() {
        System.out.println("更新一个用户");
    }

    @Override
    public void queryUser() {
        System.out.println("查找一个用户");
    }
}
```

### 2 - 自定义类实现AOP（自定义切面）（结构更简洁）

#### 1 - 自定义切面类

```java
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
```

#### 2 - XML配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl"/>
    <bean id="beforeLog" class="top.calvinhaynes.log.BeforeLog"/>
    <bean id="afterLog" class="top.calvinhaynes.log.AfterLog"/>
    
     <!--方式二：自定义类来实现AOP-->
    <bean id="diy" class="top.calvinhaynes.diy.DiyPointCut"/>

    <aop:config>
        <!--自定义切面，ref是引用的类-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* top.calvinhaynes.service.UserServiceImpl.*(..))"/>

            <!--通知(要切入的方法)-->
            <aop:before method="beforeLog" pointcut-ref="point"/>
            <aop:after method="afterLog" pointcut-ref="point"/>

        </aop:aspect>

    </aop:config>
</beans>
```

#### 3 - 测试类不变

### 3 - 使用注解实现（可读性更好）

#### 1 - 注解切面类

```java
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
```

#### 2 - XML配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="top.calvinhaynes.service.UserServiceImpl"/>
    <bean id="beforeLog" class="top.calvinhaynes.log.BeforeLog"/>
    <bean id="afterLog" class="top.calvinhaynes.log.AfterLog"/>
    
     <!--方式三：注解实现-->
    <bean id="annotationPointCut" class="top.calvinhaynes.annotation.AnnotationPointCut"/>
    
    <!--开启AOP注解支持-->
    <!--
        proxy-target-class参数：指定使用 JDK 默认 API 实现动态代理还是 Spring 自带的 cglib 实现动态代理（结果没区别）
            - true : 使用cglib
            - false : 缺省默认值，使用JDK默认API
    -->
    <aop:aspectj-autoproxy proxy-target-class="true"/>

</beans>
```

#### 3 - 测试类不变

## 扩展阅读

[Spring AOP 中切入点 Pointcut中Expression表达式解析及配置](https://cloud.tencent.com/developer/article/1455539)

[SpringAOP中的JointPoint和ProceedingJoinPoint使用详解](https://blog.csdn.net/kouryoushine/article/details/105299956)

