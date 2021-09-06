# SpringMVC-HelloSpringMVC

## 前言

此Demo重在理解过程，开发更多的是使用注解

## SpringMVC原理讲解

​	在进入页面的一瞬间用户发送请求，请求被前端控制器（请求分发器Dispatcher）拦截，前端控制器会根据请求参数生成代理请求发送到页面控制器（就是Controller），然后经过Controller中写好的业务逻辑进行数据处理和返回，返回ModelAndView到前端控制器，前端控制器使用视图解析器解析视图，并将结果返回给前端控制器，前端控制器将结果响应给用户。

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KwPOPWq00pMJiaK86lF6BjIaosVziclWLEJQkzobxHrpHcmtu2yTeVWPmEI4Yq5PaicS52VaJt8dYfQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## 根据Demo结合SpringMVC原理分析

### SpringMVC真实类过程原理图

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.2cebiia837pc.png)

### 结合Demo分析执行流程

#### 1~4  通过URL找到控制器Controller

- DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心。用户发出请求，DispatcherServlet接收请求并拦截请求。

- HandlerMapping为处理器映射。DispatcherServlet调用HandlerMapping,HandlerMapping根据请求url查找Handler。

- HandlerExecution表示具体的Handler,其主要作用是根据url查找控制器，如上url被查找控制器为：hello。

- HandlerExecution将解析后的信息传递给DispatcherServlet,解析控制器映射等。


##### **在Demo中实际应用：**

**1 - 注册DispatcherServlet**（web.xml）

```xml
 <!--1.注册DispatcherServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--关联一个springmvc的配置文件:【servlet-name】-servlet.xml-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!--启动级别-1 : 服务器启动他就跟着启动-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <!--/ 匹配所有的请求；（不包括.jsp）-->
    <!--/* 匹配所有的请求；（包括.jsp）-->
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

> / 和 /* 区别：/不会匹配实际项目中存在的jsp文件，

##### 2 - SpringMVC配置文件

配置处理器映射器和处理器适配器

```xml
<!-- 处理器映射器-->
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
<!-- 处理器适配器-->
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
```

其中URL映射注册的bean为：

```xml
<!--Handler:自己写的controller注入bean-->
<bean id="/hello" class="top.calvinhaynes.controller.HelloController"/>
```

所以1~4就是根据/hello这个URL找到HelloController这个控制器，相当于把hello返回给DispatcherServlet，然后DispatcherServlet自己解析出控制器HelloController

#### 5~8 按照规则执行Controller

- HandlerAdapter表示处理器适配器，其按照特定的规则去执行Handler。（本Demo中用的是SimpleControllerHandlerAdapter）
- Handler让具体的Controller执行。
- Controller将具体的执行信息返回给HandlerAdapter,如ModelAndView。
- HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet。

##### **在Demo中实际应用：**

HelloController.java

```java
package top.calvinhaynes.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导入Controller接口
 *
 * @author CalvinHaynes
 * @date 2021/09/05
 */
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();

        //业务逻辑代码
        
        //封装对象，放在ModelAndView中。Model
        mv.addObject("msg","HelloSpringMVC!");

        //封装要跳转的视图，放在ModelAndView中
        //: /WEB-INF/jsp/hello.jsp
        mv.setViewName("hello");

        System.out.println("Success!");

        return mv;
    }
   
}
```

==**执行HelloController，此Controller封装了一个msg对象然后设置了要跳转的视图名hello（在hello视图中展示msg对象），返回了ModelAndView对象**==

#### 9~12 视图解析

- DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的逻辑视图名。
- 视图解析器将解析的逻辑视图名传给DispatcherServlet。
- DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图。
- 最终视图呈现给用户

##### **在Demo中实际应用：**

配置视图解析器，在SpringMVC配置文件中

```xml
<!-- 视图解析器:DispatcherServlet给他的ModelAndView-->
<!-- 内置的视图解析器，未来用到很多模板引擎比如Thymeleaf，Freemaker，此处是内置的视图解析器-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="InternalResourceViewResolver">
    <!--前缀和后缀用于拼接jsp页面的路径-->
    <!--前缀-->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!--后缀-->
    <property name="suffix" value=".jsp"/>
</bean>
```

==**这里设置的前缀和后缀就是为了在DispatcherServlet拿到ModelAndView对象中包含的逻辑视图名后进行拼接拿到jsp页面的完整路径（算是视图解析器的逻辑）**==



## 遇到的问题

### 一直报404错误：

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.d2a32b2rh2g.png)

**查询之后发现是Tomcat10版本不兼容的问题，降Tomcat版本到Tomcat9.0.52，成功运行**

==下载Tomcat9.0.52之后再在IDEA中配置一个新的Tomcat9.0.52的环境，然后执行。==



### idea中web.xml报错 Servlet should have a mapping

配置springmvc时，报错，实际mapping已经写了。
工程的web.xml位置配置错误

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.3yz7zjx2b7q0.png)

### 项目依赖问题

直接导入jstl1.2的jar包会显示如上问题，经过查询，问题是在tomcat中已有jsp-api和servlet-api，但maven下载jstl时也会把它所依赖的这两个包下载下来，所以就有了包冲突，因此在maven的pom.xml中需要排除这两个包的下载

#### 此Demo完整依赖设置

```xml
  <dependencies>

        <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
        </dependency>

        <!-- JSTL（Java server pages standarded tag library，
        即JSP标准标签库）是由JCP（Java community Proces）所制定的标准规范，
        它主要提供给Java Web开发人员一个标准通用的标签库，并由Apache的Jakarta小组来维护。
        开发人员可以利用这些标签取代JSP页面上的Java代码，从而提高程序的可读性，降低程序的维护难度。-->
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl -->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet.jsp</groupId>
                    <artifactId>jsp-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jstl-impl</artifactId>
            <version>1.2</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet.jsp</groupId>
                    <artifactId>jsp-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet.jsp.jstl</groupId>
                    <artifactId>jstl-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```

### 突然子项目Module不继承父项目的依赖了，怎么办？

==删除.idea文件，重新进入项目即可解决==

