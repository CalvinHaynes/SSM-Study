# Cookie & Session --Servlet

## 前言

本Demo主要针对Servlet中关于Cookie和Session的API进行测试

## Cookie

### 1 - 什么是Cookie？

HTTP协议本身是无状态的。什么是无状态呢，即服务器无法判断用户身份。

Cookie实际上是一小段的文本信息（key-value格式）。客户端向服务器发起请求，如果服务器需要记录该用户状态，就使用response向客户端浏览器颁发一个Cookie。客户端浏览器会把Cookie保存起来。当浏览器再请求该网站时，浏览器把请求的网址连同该Cookie一同提交给服务器。服务器检查该Cookie，以此来辨认用户状态。

### 2 - Cookie运行的机制

- 客户端发送一个HttpRequest请求到服务器  

- 服务器处理请求之后发送一个HttpResponse响应到客户端，其中就包含了响应头**Set-Cookie**
- 客户端会保存Cookie，之后再向服务器发送请求的时候，HttpRequest请求中会包含请求头**Cookie**
- 服务器根据Cookie返回响应HttpResponse

![img](https://upload-images.jianshu.io/upload_images/13949989-dcf024be2733e725.png?imageMogr2/auto-orient/strip|imageView2/2/w/400/format/webp)

### 3 - Servlet中关于Cookie的常见API

```java
public Cookie[] getCookies();//返回一个数组，其中包含客户端随此请求发送的所有Cookie对象。 如果没有发送 cookie，此方法返回null 。
public String getName();//返回 cookie 的名称。 创建后无法更改名称。
public String getValue();//返回 cookie 的值。
public void setMaxAge(int expiry);//以秒为单位设置 cookie 的最大年龄。
public void addCookie(Cookie cookie);//将指定的 cookie 添加到响应中。 可以多次调用此方法来设置多个 cookie。
```

## Session

### 1 - 什么是Session?

session 从字面上讲，就是会话。这个就类似于你和一个人交谈，你怎么知道当前和你交谈的是张三而不是李四呢？对方肯定有某种特征（长相等）表明他就是张三。

session 也是类似的道理，服务器要知道当前发请求给自己的是谁。为了做这种区分，服务器就要给每个客户端分配不同的“身份标识”，然后客户端每次向服务器发请求的时候，都带上这个“身份标识”，服务器就知道这个请求来自于谁了。至于客户端怎么保存这个“身份标识”，可以有很多种方式，对于浏览器客户端，大家都默认采用 cookie 的方式。

服务器使用session把用户的信息临时保存在了服务器上，用户离开网站后session会被销毁。

### 2 - Session的运行机制

session机制是一种服务器端的机制，服务器使用一种类似于散列表的结构（也可能就是使用散列表）来保存信息。 

当程序需要为某个客户端的请求创建一个session的时候，

- 服务器首先检查这个客户端的请求里是否已包含了一个session标识 - 称为session id。
- 如果已包含一个session id则说明以前已经为此客户端创建过session，服务器就按照session id把这个session检索出来使用（如果检索不到，可能会新建一个）。
- 如果客户端请求不包含session id，则为此客户端创建一个session并且生成一个与此session相关联的session id，session id的值应该是一个既不会重复，又不容易被找到规律以仿造的字符串，这个session id将被在本次响应中返回给客户端保存。 

保存这个session id的方式可以采用cookie，这样在交互过程中浏览器可以自动的按照规则把这个标识发挥给服务器。一般这个cookie的名字都是类似于SEEESIONID

**比如：JSESSIONID=ByOK3vjFD75aPnrF7C2HmdnV6QZcEbzWoWiBYEnLerjQ99zWpBng!-145788764，它的名字就是JSESSIONID。** 

## 参考资料

https://www.jianshu.com/p/6fc9cea6daa2

https://www.cnblogs.com/lonelydreamer/p/6169469.html

