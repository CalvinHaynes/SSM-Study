# SpringMVC实战开发

## 注解开发整理

### 1 - **web.xml写死**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
 Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                      https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
         version="5.0"
         metadata-complete="true">

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

### 2 - **SpringMVC配置文件写死**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="top.calvinhaynes.controller"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping注解生效
        必须向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driven配置帮助我们自动完成上述两个实例的注入。
     -->
    <mvc:annotation-driven />

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```

### 3 - **专注于使用注解写Controller**

```java
package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    String hello(Model model){
        model.addAttribute("msg","HelloSpringMVC,now I use Annotation!");

        return "hello";
    }
}
```

使用springMVC必须配置的三大件：

==**处理器映射器、处理器适配器、视图解析器**==

通常，我们只需要**手动配置视图解析器**，而**处理器映射器**和**处理器适配器**只需要开启**注解驱动**即可，而省去了大段的xml配置

## 结果跳转方式

### ModelAndView

设置ModelAndView对象 , 根据view的名称 , 和视图解析器跳到指定的页面 .

页面 : {视图解析器前缀} + viewName +{视图解析器后缀}

```xml
<!-- 视图解析器 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
     id="internalResourceViewResolver">
   <!-- 前缀 -->
   <property name="prefix" value="/WEB-INF/jsp/" />
   <!-- 后缀 -->
   <property name="suffix" value=".jsp" />
</bean>
```

对应的controller类

```java
public class ControllerTest1 implements Controller {

   public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
       //返回一个模型视图对象
       ModelAndView mv = new ModelAndView();
       mv.addObject("msg","ControllerTest1");
       mv.setViewName("test");
       return mv;
  }
}
```

### ServletAPI

通过设置ServletAPI , 不需要视图解析器 .

1、通过HttpServletResponse进行输出

2、通过HttpServletResponse实现重定向

3、通过HttpServletResponse实现转发

```java
@Controller
public class ResultGo {

   @RequestMapping("/result/t1")
   public void test1(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
       rsp.getWriter().println("Hello,Spring BY servlet API");
  }

   @RequestMapping("/result/t2")
   public void test2(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
       rsp.sendRedirect("/index.jsp");
  }

   @RequestMapping("/result/t3")
   public void test3(HttpServletRequest req, HttpServletResponse rsp) throws Exception {
       //转发
       req.setAttribute("msg","/result/t3");
       req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,rsp);
  }

}
```

### SpringMVC

**通过SpringMVC来实现转发和重定向 - 无需视图解析器；**

重定向 , 不需要视图解析器 , 本质就是重新请求一个新地方嘛 , 所以一定要注意路径问题.

测试前，需要将视图解析器注释掉

```java
/**
 * 转发和重定向
 *
 * 通过SpringMVC来实现转发和重定向 - 无需视图解析器
 * 测试前，需要将视图解析器注释掉
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class ForwardAndRedirect {

    @RequestMapping("/far/t1")
    public String test1() {

        //转发：方式一
        return "/WEB-INF/jsp/hello.jsp";
    }

    @RequestMapping("/far/t2")
    public String test2() {

        //转发：方式二
        return "forward:/WEB-INF/jsp/hello.jsp";
    }

    @RequestMapping("/far/t3")
    public String test3() {
        //重定向
        return "redirect:/index.jsp";
    }
}
```

**通过SpringMVC来实现转发和重定向 - 有视图解析器；**

可以重定向到另外一个请求实现 .

```java
/**
 * 通过SpringMVC来实现转发和重定向 - 有视图解析器；
 * 可以重定向到另外一个请求实现 .
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class ForwardAndRedirect2 {

    @RequestMapping("/far2/t1")
    public String test1() {
        //转发
        return "hello";
    }

    @RequestMapping("/far2/t2")
    public String test2() {
        //重定向
//        return "redirect:/index.jsp";

        //重定向到另一个请求
        return "redirect:/restful/5/5";
    }
}
```

## 数据处理

### 处理提交数据

**1、提交的域名称和处理方法的参数名一致**

提交数据 : http://localhost:8080/hello2?name=CalvinHaynes

处理方法 :

```java
/**
     * 提交的域名称和处理方法的参数名一致
     *
     * @param name 的名字
     * @return {@link String}
     */
    @RequestMapping("/hello2")
    public String hello2(String name) {
        System.out.println(name);
        return "hello";
    }
```

后台输出 : CalvinHaynes

**2、提交的域名称和处理方法的参数名不一致**

提交数据 : http://localhost:8080/hello3?username=CalvinHaynes

处理方法 :

```java
/**
     * 提交的域名称和处理方法的参数名不一致
     *
     * @param name 的名字
     * @return {@link String}
     */
    @RequestMapping("/hello3")
    public String hello3(@RequestParam("username") String name) {
        System.out.println(name);
        return "hello";
    }
```

后台输出 : CalvinHaynes

**3、提交的是一个对象**

要求提交的表单域和对象的属性名一致  , 参数使用对象即可

1、实体类

```java
package top.calvinhaynes.pojo;

/**
 * 用户
 * The type User.
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
public class User {
    private int id;
    private String username;
    private String password;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

```

2、提交数据 : http://localhost:8080/user?id=1&username=jack&password=123456

3、处理方法 :

```java
/**
     * 用户
     *
     * @param user 用户
     * @return {@link String}
     */
    @RequestMapping("/user")
    public String user(User user){
        System.out.println(user);
        return "hello";
    }
```

后台输出 : User { id=1, username=‘jack’,password=123456}

说明：如果使用对象的话，前端传递的参数名和对象名必须一致，否则就是null。

## 处理编码问题

### 1 - SpringMVC自提供的编码过滤器

==在web.xml中配置该过滤器，修改后重启服务器==

```xml
<filter>
   <filter-name>encoding</filter-name>
   <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
   <init-param>
       <param-name>encoding</param-name>
       <param-value>utf-8</param-value>
   </init-param>
</filter>
<filter-mapping>
   <filter-name>encoding</filter-name>
   <url-pattern>/*</url-pattern>
</filter-mapping>
```

**有些极端情况下上述过滤器对get请求的支持不好 .**

采用以下方式更好：

### 2 - 修改Tomcat配置文件

==server.xml配置文件修改Connector==

```xml
<Connector URIEncoding="utf-8" port="8080" protocol="HTTP/1.1"
          connectionTimeout="20000"
          redirectPort="8443" />
```

### 3 - 自定义过滤器

```java
package top.calvinhaynes.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 通用编码过滤器
 * 解决get和post请求 全部乱码的过滤器
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
public class GenericEncodingFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //处理response的字符编码
        HttpServletResponse myResponse=(HttpServletResponse) response;
        myResponse.setContentType("text/html;charset=UTF-8");

        // 转型为与协议相关对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 对request包装增强
        HttpServletRequest myrequest = new MyRequest(httpServletRequest);
        chain.doFilter(myrequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}

//自定义request对象，HttpServletRequest的包装类
class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    //是否编码的标记
    private boolean hasEncode;
    //定义一个可以传入HttpServletRequest对象的构造函数，以便对其进行装饰
    public MyRequest(HttpServletRequest request) {
        super(request);// super必须写
        this.request = request;
    }

    // 对需要增强方法 进行覆盖
    @Override
    public Map getParameterMap() {
        // 先获得请求方式
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post")) {
            // post请求
            try {
                // 处理post乱码
                request.setCharacterEncoding("utf-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (method.equalsIgnoreCase("get")) {
            // get请求
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!hasEncode) { // 确保get手动编码逻辑只运行一次
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            try {
                                // 处理get乱码
                                values[i] = new String(values[i]
                                        .getBytes("ISO-8859-1"), "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode = true;
            }
            return parameterMap;
        }
        return super.getParameterMap();
    }

    //取一个值
    @Override
    public String getParameter(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        return values[0]; // 取回参数的第一个值
    }

    //取所有值
    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        return values;
    }
}
```

## Model、ModelMap、ModelAndView区别

`Model`是一个接口，而`ModelMap`是一个类。

`ModelAndView`只是一个`ModelMap`和一个视图对象的容器。 它允许一个控制器作为一个单一的值返回。

**Model，ModelMap和ModelAndView之间的区别**

- **Model：**它是一个接口。 它为模型属性定义了一个持有者，主要是为了向模型添加属性而存在的。

```java
   @RequestMapping(method = RequestMethod.GET)
    public String printHello(Model model) {
        model.addAttribute("message", "Hello World!!");
        return "hello";
    }
```

- **ModelMap：**在构build用于UI工具的模型数据时使用的Map的实现。支持链式调用和模型属性名称的生成。

```java
 @RequestMapping("/helloworld")
    public String hello(ModelMap map) {
        String helloWorldMessage = "Hello world!";
        String welcomeMessage = "Welcome!";
        map.addAttribute("helloMessage", helloWorldMessage);
        map.addAttribute("welcomeMessage", welcomeMessage);
        return "hello";
    }
```

- **ModelAndView：**这个类仅仅是为了让控制器可以返回一个返回值中的模型和视图。

```java
@RequestMapping("/welcome")
public ModelAndView helloWorld() { 
    String message = "Hello World!"; 
    return new ModelAndView("welcome", "message", message); 
} 
```

**Model** ： 只有寥寥几个方法只适合用于储存数据，简化了新手对于Model对象的操作和理解，是一个包含四个addAttribute和一个mergeAttributes方法的接口。

**ModelMap** ： 继承了 LinkedMap ，除了实现了自身的一些方法，同样的继承 LinkedMap 的方法和特性；

**ModelAndView** ：可以在储存数据的同时，进行设置返回的逻辑视图，进行控制展示层的跳转。

