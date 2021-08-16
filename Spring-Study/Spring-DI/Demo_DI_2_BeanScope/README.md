# Demo_DI_2_BeanScope

## 前言

- 本 Demo 主要体现了Bean的Scope（作用域）

- 按照官网的解释：bean就像是写好的 recipe 菜单一样，通过设置Bean的作用域，可以控制Bean创建对象的范围
- Spring Framework 支持六个 BeanScope
  ![BeanScope](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.tvmxb96a8ow.png)

## 本Demo中只体现了前两个Scope

### 1 - Singleton
当一个bean的作用域为Singleton，那么Spring IoC容器中只会存在一个共享的bean实例，并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例。Singleton是单例类型，就是在创建起容器时就同时自动创建了一个bean的对象，不管你是否使用，他都存在了，每次获取到的对象都是同一个对象。注意，Singleton作用域是Spring中的缺省作用域。


```xml
<bean id="user1" class="top.calvinhaynes.pojo.User" scope="singleton">
        <property name="name" value="小红"/>
        <property name="age" value="15"/>
    </bean>

    <!--注意，Singleton作用域是Spring中的缺省作用域。-->
    <bean id="user0" class="top.calvinhaynes.pojo.User" >
        <property name="name" value="小红"/>
        <property name="age" value="15"/>
    </bean>
```

### 2 - Prototype
当一个bean的作用域为Prototype，表示一个bean定义对应多个对象实例。Prototype作用域的bean会导致在每次对该bean请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）时都会创建一个新的bean实例。Prototype是原型类型，它在我们创建容器的时候并没有实例化，而是当我们获取bean的时候才会去创建一个对象，而且我们每次获取到的对象都不是同一个对象。根据经验，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用singleton作用域。在XML中将bean定义成prototype，可以这样配置：

```xml
<bean id="user" class="top.calvinhaynes.pojo.User" scope="prototype">
        <property name="name" value="小明"/>
        <property name="age" value="18"/>
    </bean>
```
