# HelloServlet

## 前言

- **Java Servlet**（Java服务器小程序）是一个基于Java技术的Web组件，运行在服务器端，它由Servlet**容器**所管理，用于生成动态的内容。
  -  Servlet是平台独立的Java类，编写一个Servlet，实际上就是按照Servlet规范编写一个Java类。把实现了 Servlet 接口的 Java 程序叫做Servlet。
  - Servlet被编译为平台独立 的字节码，可以被==动态==地加载到支持Java技术的Web服务器中运行。
  - HTTP 协议只是一个规范，定义服务请求和响应的大致式样。Java servlet 类**将HTTP中那些低层的结构包装在 Java 类中**，这些类所包含的便利方法使其在 Java 语言环境中更易于处理。

- **容器**（如 Tomcat）将为 servlet 管理运行时环境。您可以配置该容器，定制 J2EE 服务器的工作方式，以便将 servlet 暴露给外部世界。正如我们将看到的，通过该容器中的各种配置文件，您在 URL（由用户在浏览器中输入）与服务器端组件之间搭建了一座桥梁，这些组件将处理您需要该 URL 转换的请求。在运行应用程序时，该==容器将**加载并初始化 servlet**，**管理其生命周期**==。

- Sun 在这些 API 中提供一个接口叫做：Servlet，如果你想开发一个Servlet程序，只需要完成两个小步骤：
  - 编写一个类，实现Servlet接口。
  - 把开发好的Java类部署到web服务器中。

> 正如您正使用的特定 servlet 容器的配置文件中所定义的，当用户通过 URL 发出一个请求时，这些 Java servlet 类就将之转换成一个 ==HttpServletRequest==，并发送给 URL 所指向的目标。当服务器端完成其工作时，Java 运行时环境（Java Runtime Environment）就将结果包装在一个 ==HttpServletResponse== 中，然后将原 HTTP 响应送回给发出该请求的客户机。在与 Web 应用程序进行交互时，通常会发出多个请求并获得多个响应。所有这些都是在一个会话语境中，Java 语言将之包装在一个 ==HttpSession== 对象中。在处理响应时，您可以访问该对象，并在创建响应时向其添加事件。它提供了一些跨请求的语境。
>

- Serlvet 接口 Sun 公司有两个默认的实现类：==HttpServlet==，==GenericServlet==

## Tomcat原理

## Servlet和Tomcat的关系

## 实战Demo：HelloServlet

==结合项目查看以下步骤==

### 1 - 创建Maven 基于 webapp模板的项目

![img](https://img-blog.csdnimg.cn/20201030195424154.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

### 2 - 添加必要的Java源代码目录和resources资源目录

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.4nxwi2otcps0.png)

### 3 - 修改Web.XML配置文件与Tomcat中的版本一致

web.xml文件是Java web项目中的一个配置文件，主要用于配置欢迎页、Filter、Listener、Servlet等，但并不是必须的，一个java web项目没有web.xml文件照样能跑起来。Tomcat容器`/conf`目录下也有作用于全局web应用web.xml文件，当一个web项目要启动时，Tomcat会首先加载项目中的web.xml文件，然后通过其中的配置来启动项目，只有配置的各项没有错误时，项目才能正常启动。

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


</web-app>
```

### 4 - 编写HelloServlet类实现HTTPServlet接口

​		由于用到了HTTPServlet接口，一般Servlet的框架是由两个Java包组成的：javax.servlet与javax.servlet.http。在javax.servlet包中定义了所有的Servlet类都必须实现或者扩展的通用接口和类。在javax.servlet.http包中定义了采用Http协议通信的HttpServlet类。

​		但是在利用Maven导入依赖的时候一再出错，所以我采用了导入Tomcat内置的Servlet的办法，源代码没差

#### 导入项目依赖的Jar包

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.1p4hmjhtpyhs.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.6a5t2oqbgwc0.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.2kp5ovdhdhe0.png)

#### 编写HelloServlet类

```java
package top.calvinhaynes.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * hello servlet
 * http://localhost:8080/servlet/helloServlet
 *
 * @author CalvinHaynes
 * @date 2021/08/25
 */
public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Hello Servlet!");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

### 5 - 编写 Servlet 的映射

为什么需要映射：我们写的是 Java 程序，但是要通过浏览器访问，而浏览器需要连接 Web 服务器，所以我们需要再 Web 服务中注册我们写的 Servlet，还需给他一个浏览器能够访问的路径；

**web.xml添加如下配置：**

```xml
<!--注册servlet  -->
<servlet>
    <servlet-name>helloServlet</servlet-name>
    <servlet-class>top.calvinhaynes.servlet.HelloServlet</servlet-class>
</servlet>

<!--Servlet的请求路径映射配置  -->
<servlet-mapping>
    <servlet-name>helloServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

### 6 - 配置TomCat

#### 1.增加配置

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.73md2zl1iug0.png)

#### 2.填写Tomcat配置

![请添加图片描述](https://img-blog.csdnimg.cn/20201030201259893.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

#### 3.部署到Maven项目中（Fix上图的warning）

将Tomcat环境部署到我们想要使用Tomcat的JavaWeb项目中，可以通过下面的Application Context配置访问项目的URL地址

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.10eyrofk3bcw.png)

### 7 - 测试

测试 http://localhost:8080/helloservlet/hello

## 注意事项和建议

- 建议每次将一个新项目部署到Tomcat中时，单个项目部署，这样提高Tomcat速度，多个项目Tomcat会一起打包，速度较慢

## 参考资料

https://www.jianshu.com/p/7dcd2c689729

