# Demo_AOP_1_JavaBase_Reflection
## 前言
由于AOP底层离不开代理模式，而动态代理模式的底层实现又是反射，所以在学习代理模式之前先学习一下Java中的反射，并且简单说一下注解

## 注解
- Annotation是从JDK5.0开始引入的新技术
- Annotation作用
  - 不是程序本身，可以对程序做出解释（这一点和注释没区别）
  - **可以被其他程序读取（编译器等）**
- Annotation格式
  - @注解名(参数：格式：value="")
  - @SuppressWarnings(value="unchecked")
- Annotation在哪里使用？
  - 可以附加在package，class，method，field上面，相当于给它们添加了额外的辅助信息，我们可以通过反射机制编程实现对这些元数据的访问

### 1 - 内置注解
![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.dmygzx39fkw.png)

### 2 - 元注解

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.4o7nfdqmas20.png)

### 3 - 自定义注解

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.6m46z4umrkg0.png)

#### 自定义注解应用的例子
当然可以。假设我们正在开发一个简单的 RESTful API 框架，我们想使用注解来定义 HTTP 端点和相关的方法。下面是如何使用自定义注解实现这一目标的示例。

首先，定义一个 `@Endpoint` 注解来标识类为 RESTful 控制器，并定义一个 `@Route` 注解来标识方法和相关的 HTTP 路径：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Endpoint {
    String value();  // Base path for this controller, e.g., "/users"
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Route {
    String path();   // Path for this method, e.g., "/{id}"
    String method(); // HTTP method, e.g., "GET", "POST"
}
```

接下来，使用这些注解定义一个简单的用户控制器：

```java
@Endpoint("/users")
public class UserController {

    @Route(path="/{id}", method="GET")
    public User getUserById(int id) {
        // Fetch and return user by ID
    }

    @Route(path="/", method="POST")
    public void createUser(User user) {
        // Create a new user
    }
}
```

现在，当我们的框架启动时，它可以通过反射扫描所有的类，查找带有 `@Endpoint` 和 `@Route` 注解的类和方法，并自动为它们注册相应的 HTTP 路由。

例如，我们的框架可能会有这样的启动逻辑：

```java
public void initializeRoutes() {
    for (Class<?> cls : allClassesInProject) {
        if (cls.isAnnotationPresent(Endpoint.class)) {
            Endpoint endpoint = cls.getAnnotation(Endpoint.class);
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Route.class)) {
                    Route route = method.getAnnotation(Route.class);
                    // Register this method as an HTTP route, e.g.,
                    // GET /users/{id} -> UserController.getUserById
                    registerRoute(endpoint.value() + route.path(), route.method(), method);
                }
            }
        }
    }
}
```

这只是一个简化的示例，但它展示了如何使用自定义注解来简化框架的配置和使用，并使得代码更加清晰和声明式。

## 反射

### 1 - 静态语言VS动态语言

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.6lbxn91wcvw0.png)

### 2 - 反射概述

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.3nseloayqom0.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.3h83b8iggb40.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.6cd63sxz01c0.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.21zkdak5rmrk.png)

### 3 - Class类

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.2pt3748gq700.png)

- #### ==获取Class类的方法==

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.74cjz23d9580.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.45zs5i6iod40.png)

- #### 哪些类型可以有Class对象？

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.5jf81o1zfg40.png)

```java
	 	Class c1 = Object.class;        //类
        Class c2 = Comparable.class;    //接口
        Class c3 = String[].class;      //一维数组
        Class c4 = int[][].class;       //二维数组
        Class c5 = Override.class;      //注解
        Class c6 = ElementType.class;   //枚举类
        Class c7 = Integer.class;       //基本类型包装类
        Class c8 = void.class;          //关键字
        Class c9 = Class.class;         //Class类
```

### 4 - 类加载过程的内存分析

- #### Java内存

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.3gcvn2sd52s0.png)

- #### 类的加载过程

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.49x2sxce9u60.png)

- #### 过程详解

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.57sr1xrdr8w0.png)

- #### 类加载器

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.1gfh0elxe00w.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.2dlu9tboe728.png)

- #### 类的初始化发生的场景

  - 类的主动引用（一定发生类的初始化）

  ![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.51vdoh6x84o0.png)

  - 类的被动引用（不会发生类的初始化）

  ![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.2w7e9e2fehg0.png)

- #### 双亲委派机制(Delegation Model)

  ##### 1 - **双亲委派机制**

  　　双亲委派机制是指当一个类加载器收到一个类加载请求时，该类加载器首先会把请求委派给父类加载器。每个类加载器都是如此，只有在父类加载器在自己的搜索范围内找不到指定类时，子类加载器才会尝试自己去加载。
  ![](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage20231017093430.png)
  ![](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage20231017093448.png)
  ![](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImageJava%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%B4%BE%E6%9C%BA%E5%88%B6.png)
  ##### 2 - 双亲委派模型工作工程：

  　　1.当Application ClassLoader 收到一个类加载请求时，他首先不会自己去尝试加载这个类，而是将这个请求委派给父类加载器Extension ClassLoader去完成。 

  　　2.当Extension ClassLoader收到一个类加载请求时，他首先也不会自己去尝试加载这个类，而是将请求委派给父类加载器Bootstrap ClassLoader去完成。  

  　　3.如果Bootstrap ClassLoader加载失败(在<JAVA_HOME>\lib中未找到所需类)，就会让Extension ClassLoader尝试加载。 

  　　4.如果Extension ClassLoader也加载失败，就会使用Application ClassLoader加载。 

  　　5.如果Application ClassLoader也加载失败，就会使用自定义加载器去尝试加载。 

  　　6.如果均加载失败，就会抛出ClassNotFoundException异常。

  例子：

  　　当一个Hello.class这样的文件要被加载时。不考虑我们自定义类加载器，首先会在AppClassLoader中检查是否加载过，如果有那就无需再加载了。如果没有，那么会拿到父加载器，然后调用父加载器的loadClass方法。父类中同理会先检查自己是否已经加载过，如果没有再往上。注意这个过程，直到到达Bootstrap classLoader之前，都是没有哪个加载器自己选择加载的。如果父加载器无法加载，会下沉到子加载器去加载，一直到最底层，如果没有任何加载器能加载，就会抛出ClassNotFoundException。

### 5 - 获取运行时类的完整结构

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.2vgik1drvmo0.png)

### 6 - 获取之后使用场景

- #### 创建类的对象

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.7730oeure4k0.png)

- #### ==调用方法==

  ![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.2kkbzzzrffe0.png)

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.3ntvrtja42c0.png)

- #### setAccessible访问安全检查开关

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.5jl4q8fj2ko0.png)

- #### 关闭访问安全检查提高反射效率的测试

```java
public class Test9 {

    //普通方式调用
    public static void test1(){

        User user = new User();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("普通方式调用方法执行10亿次用时：" + (endTime - startTime) + "ms");


    }

    //反射方式调用
    public static void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user1 = new User();
        Class userClass = user1.getClass();

        Method getName = userClass.getDeclaredMethod("getName", null);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user1,null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("反射方式调用方法执行10亿次用时：" + (endTime - startTime) + "ms");
    }

    //禁用安全检查
    public static void test3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user2 = new User();
        Class userClass = user2.getClass();

        Method getName = userClass.getDeclaredMethod("getName", null);
        getName.setAccessible(true);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user2, null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("禁用安全检查调用方法执行10亿次用时：" + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test1();
        test2();
        test3();
    }
}

```

```
//    普通方式调用方法执行10亿次用时：6ms
//    反射方式调用方法执行10亿次用时：3210ms
//    禁用安全检查调用方法执行10亿次用时：1666ms

//	  可见效率提高了一倍
```

