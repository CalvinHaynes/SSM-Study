## Servlet原理

- Servlet的框架是由两个Java包组成的：javax.servlet与javax.servlet.http。（Tomcat内置的Servlet：jakarta.servlet和jakarta.servlet.http）
  - 在javax.servlet包中定义了所有的Servlet类都必须实现或者扩展的通用接口和类。
  - 在javax.servlet.http包中定义了采用Http协议通信的HttpServlet类。
- Servlet的框架的核心是javax.servlet.Servlet接口，所有的Servlet都必须实现这个接口。
- 接口中定义了五个方法：

```java
init(ServletConfig)方法：负责初始化Servlet对象，在Servlet的生命周期中，该方法执行一次；该方法执行在单线程的环境下，因此开发者不用考虑线程安全的问题；
service(ServletRequest req,ServletResponse res)方法：负责响应客户的请求；为了提高效率，Servlet规范要求一个Servlet实例必须能够同时服务于多个客户端请求，即service()方法运行在多线程的环境下，Servlet开发者必须保证该方法的线程安全性；
destroy()方法：当Servlet对象退出生命周期时，负责释放占用的资源；
getServletInfo：就是字面意思，返回Servlet的描述；
getServletConfig：这个方法返回由Servlet容器传给init方法的ServletConfig。
```

- 涉及到的类

  - **ServletRequest & ServletResponse**:对于每一个HTTP请求，servlet容器会创建一个封装了HTTP请求的ServletRequest实例传递给servlet的service方法，ServletResponse则表示一个Servlet响应，其隐藏了将响应发给浏览器的复杂性。通过ServletRequest的方法你可以获取一些请求相关的参数，而ServletResponse则可以将设置一些返回参数信息，并且设置返回内容。

  - **HTTPServlet**:在编写Servlet应用程序时，大多数都要用到HTTP，也就是说可以利用HTTP提供的特性，javax.servlet.http包含了编写Servlet应用程序的类和接口，其中很多覆盖了javax.servlet中的类型，我们自己在编写应用时大多时候也是继承的HttpServlet。

    