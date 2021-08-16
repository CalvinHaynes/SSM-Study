# Demo_DI_4_Annotation

## 前言
利用**注解**实现DI/IOC

**xml与注解整合开发** ：推荐最佳实践
- xml管理Bean
- 注解只用来完成属性注入
- 使用过程中，只需要注意一个问题，想使用注解，就需要在配置文件中配置注解让其生效
```xml
    <!--引入约束-->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--指定注解扫描包，此包下的注解就会生效-->
    <context:component-scan base-package="top.calvinhaynes.pojo"/>
    <context:annotation-config/>
```