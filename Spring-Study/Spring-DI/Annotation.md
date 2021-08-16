# 常用注解总结

### 1 - @Autowired
根据类型进行自动装配
- @Autowired应用到构造器上，表示利用有参构造器进行自动装配
```java
public class MovieRecommender {

    private final CustomerPreferenceDao customerPreferenceDao;

    @Autowired
    public MovieRecommender(CustomerPreferenceDao customerPreferenceDao) {
        this.customerPreferenceDao = customerPreferenceDao;
    }

    // ...
}
```
- 应用于传统的Set方法上
```java
public class SimpleMovieLister {

    private MovieFinder movieFinder;

    @Autowired
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // ...
}
```
- 应用在类的方法上
```java

public class MovieRecommender {

    private MovieCatalog movieCatalog;

    private CustomerPreferenceDao customerPreferenceDao;

    @Autowired
    public void prepare(MovieCatalog movieCatalog,
            CustomerPreferenceDao customerPreferenceDao) {
        this.movieCatalog = movieCatalog;
        this.customerPreferenceDao = customerPreferenceDao;
    }

    // ...
}
```
- 直接应用到类的属性上，下例还体现了和Set方法上面的一个@Autowired注解结合使用
```java
public class MovieRecommender {

    private final CustomerPreferenceDao customerPreferenceDao;

    @Autowired
    private MovieCatalog movieCatalog;

    @Autowired
    public MovieRecommender(CustomerPreferenceDao customerPreferenceDao) {
        this.customerPreferenceDao = customerPreferenceDao;
    }

    // ...
}

```
- 设置非必须注入的属性值 required
  - false：注入属性可以为空
  - true：缺省值，注入属性不能是空的
```java
public class SimpleMovieLister {

    private MovieFinder movieFinder;

    //这里表面注入movieFinder这个属性不能是空的
    @Autowired(required = false)
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // ...
}
```

#### 1 - @Nullable ： 另一种设置注入属性null-safty，就是空值安全性设置的一个辅助注解
```java
public class SimpleMovieLister {

    @Autowired
    public void setMovieFinder(@Nullable MovieFinder movieFinder) {
        //...
    }
}
```

#### 2 - @Qualifier ： 与@Autowired配合使用，加上@Qualifier可以依据名称自动装配
```java
public class MovieRecommender {

    @Autowired
    //将以下属性与 main 名称（相当于 bean 的 id）相联系，使得可以根据名称自动装配
    @Qualifier("main")
    private MovieCatalog movieCatalog;

    // ...
}
```

### 2 - @Component
组件注解，相当于代替了XML文件中的bean标签
```java
package top.calvinhaynes.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("person")
// 相当于配置文件中 <bean id="user" class="当前注解的类"/>
public class Person {
    //不用提供set方法,直接Value注解向类的属性注入值
//    @Value("Calvin Haynes")
    public String name;

    //提供Set方法
    @Value("Calvin Haynes")
    public void setName(String name) {
        this.name = name;
    }
}

```
#### 1 - @Value ： 注入值注解
@Value 通常用于注入外化属性：
```java
@Component
public class MovieRecommender {

    private final String catalog;

    public MovieRecommender(@Value("${catalog.name}") String catalog) {
        this.catalog = catalog;
    }
}

```
![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.2me3v1e5l920.png)

#### 2 - @Component 衍生的三个注解
- @Controller：web层
- @Service：service层
- @Repository：dao层

### 3 - @Scope
指定Bean的Scope的注解
```java
@Scope("prototype")
@Repository
public class MovieFinderImpl implements MovieFinder {
    // ...
}
```

