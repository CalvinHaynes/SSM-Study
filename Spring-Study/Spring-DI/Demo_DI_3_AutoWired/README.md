# Demo_DI_3_AutoWired

## 前言
AutoWired 自动装配，Spring可以根据设置的自动装配策略实施相应的猜测，猜测bean的名称和类型是否符合条件，从而进行依赖的自动注入，也就是所谓的自动装配

自动装配是使用spring满足bean依赖的一种方法，spring会在应用上下文中为某个bean寻找其依赖的bean。

装配：装配就是指注入依赖

Spring有三种装配机制：
- 在xml中显式配置；
- 在Java Config中设置
- 隐式的bean发现机制和自动装配。（这种就是本Demo的内容）

Spring自动装配主要从两个角度来实现，或者说是两个操作
1. 组件扫描（Component Scanning）：spring会自动发现应用上下文中所创建的bean；
2. 自动装配（AutoWiring）：spring自动满足bean之间的依赖，也就是我们说的IoC/DI；

## 本Demo中的自动装配实现
### 1 - 方式1 - XML配置文件实现
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dog" class="top.calvinhaynes.pojo.Dog"/>
<!--    <bean id="dog1" class="top.calvinhaynes.pojo.Dog"/>-->
    <bean id="cat" class="top.calvinhaynes.pojo.Cat"/>
<!--    <bean id="cat1" class="top.calvinhaynes.pojo.Cat"/>-->

    <!--非自动装配的设置 -->
<!--    <bean id="person" class="top.calvinhaynes.pojo.Person">-->
<!--        <property name="name" value="李华"/>-->
<!--        <property name="cat" ref="cat"/>-->
<!--        <property name="dog" ref="dog"/>-->
<!--    </bean>-->

    <!--自动装配设置-->
    <!--autowire="byName"，按名称自动装配-->
    <!--当一个bean节点带有 autowire byName的属性时。-->
    <!--    - 将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。-->
    <!--    - 去spring容器中寻找是否有此字符串名称id的对象。-->
    <!--    - 如果有，就取出注入；如果没有，就报空指针异常。-->
    <bean id="person1" class="top.calvinhaynes.pojo.Person" autowire="byName">
        <property name="name" value="Calvin Haynes"/>
    </bean>

    <!--按类型自动装配 -->
    <!--使用autowire byType首先需要保证：同一类型的对象，在spring容器中唯一。如果不唯一，会报不唯一的异常。 -->
    <bean id="person2" class="top.calvinhaynes.pojo.Person" autowire="byType">
        <property name="name" value="李华"/>
    </bean>

    <!--利用constructor进行自动装配-->
    <!--找到自动注入对应的有参构造器进行自动注入 -->
    <bean id="person3" class="top.calvinhaynes.pojo.Person" autowire="constructor">
        <property name="name" value="小狗"/>
    </bean>
</beans>
```

### 2 - 注解实现
实现自动装配的注解：
- 1、@Autowired ： 根据类型自动装配
  - 注解参数 required
    - @Autowired(required=false) : 说明对象可以为null
    - @Autowired(required=true) : 说明对象不可以为null
- 2、@Qualifier : 与@Autowired配合使用，加上@Qualifier可以依据名称自动装配
```java
@Autowired
@Qualifier(value="名称")
```
- 3、@Resource:
  - @Resource如有指定的name属性，先按该属性进行byName方式查找装配； 
  - 其次再进行默认的byName方式进行装配； 
  - 如果以上都不成功，则按byType的方式自动装配。 
  - 都不成功，则报异常。

### 3 - @Autowired与@Resource异同：


1、@Autowired与@Resource都可以用来装配bean。都可以写在字段上，或写在setter方法上。

2、@Autowired默认按类型装配（属于spring规范），默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用

3、@Resource（属于J2EE复返），默认按照名称进行装配，名称可以通过name属性进行指定。如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。

它们的作用相同都是用注解方式注入对象，但执行顺序不同。@Autowired先byType，@Resource先byName。
