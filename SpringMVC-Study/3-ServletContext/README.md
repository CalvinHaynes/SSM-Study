# ServletContext

web容器在启动的时候，它会为每个web程序都创建一个对应的ServletContext对象，它代表了当前的web应用；

**ServletContext和Cookie、Session做一个简单对比（Cookie和Session在之后）**：

![1](https://atts.w3cschool.cn/attachments/image/20180428/1524881565403839.png)

关于ServletContext，其主要有四种作用：

- 数据共享
- 请求转发
- 获取Web应用初始化参数
- 读取资源文件

==其中，数据共享是其主要的作用，其余的均不重要，且有一些用其他方式替代更佳。==

## 数据共享

- 多个Servlet可以通过ServletContext对象来实现数据间的共享

- 类似于Session，通过ServletContext对象我们也可以实现数据共享，但值得注意的是，Session是只能在一个客户端中共享数据，而ServletContext中的数据是在所有客户端中都可以实现数据共享的。

**此部分在本Demo中的`top.calvinhaynes.servlet.GetServletContext`中**

## 请求转发

- 请求转发是服务器行为. 

- 当客户端请求一个资源, 服务器会从另外的位置(可能是当前的域名, 也可能是别的域名)请求获取这 个资源, 然后返回给客户端.

**此部分在本Demo中的`top.calvinhaynes.servlet.ForwordRequest`中**

## 获取Web应用初始化参数

**初始化参数的设置**：web初始化参数可以通过web.xml文件中进行设置，
标签是`<context-param>`和子标签`<param-name>`、`<param-value>`

**此部分在本Demo中的`top.calvinhaynes.servlet.GetWebInitParam`中**

## 读取资源文件

读取resources文件夹中的db.properties文件

**此部分在本Demo中的`top.calvinhaynes.servlet.ObtainResource`中**

## web.xml配置

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

    <!--    Web应用初始化参数设置-->
    <context-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </context-param>

    <!--    基本Servlet应用：设置ServletContext的Attribute-->
    <servlet>
        <servlet-name>servlet</servlet-name>
        <servlet-class>top.calvinhaynes.servlet.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>servlet</servlet-name>
        <url-pattern>/servlet</url-pattern>
    </servlet-mapping>

    <!--    读取ServletContext-->
    <servlet>
        <servlet-name>servletContext</servlet-name>
        <servlet-class>top.calvinhaynes.servlet.GetServletContext</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>servletContext</servlet-name>
        <url-pattern>/gsc</url-pattern>
    </servlet-mapping>

    <!--    获取Web应用初始化参数-->
    <servlet>
        <servlet-name>getInitParam</servlet-name>
        <servlet-class>top.calvinhaynes.servlet.GetWebInitParam</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>getInitParam</servlet-name>
        <url-pattern>/gip</url-pattern>
    </servlet-mapping>

    <!--    请求转发-->
    <servlet>
        <servlet-name>forwardRequest</servlet-name>
        <servlet-class>top.calvinhaynes.servlet.ForwordRequest</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>forwardRequest</servlet-name>
        <url-pattern>/fr</url-pattern>
    </servlet-mapping>

    <!--    获取资源文件-->
    <servlet>
        <servlet-name>obtainResource</servlet-name>
        <servlet-class>top.calvinhaynes.servlet.ObtainResource</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>obtainResource</servlet-name>
        <url-pattern>/or</url-pattern>
    </servlet-mapping>

</web-app>
```