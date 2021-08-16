# Demo_DI_5_JavaConfig
## 前言

JavaConfig : 

- 完全由Java文件进行Spring项目的配置，彻底拜托XML文件复杂的配置，主要是利用注解在Java文件中进行配置
- JavaConfig 是Spring的一个子项目，就是用来替代XML文件配置Spring项目的

## 本Demo
**JavaConfig的核心部件就是**
- @Configuration-annotated 类
- @Bean-annotated 方法
###1 - 相关注解（也是常用注解）
- @Configuration
  - 在一个类上写此注解，表明这个类是JavaConfig配置类，类似于XML文件可以对Bean进行配置（用到@Bean注解）
    ```java
    @Configuration
    public class AppConfig {
    
        @Bean
        public MyService myService() {
            return new MyServiceImpl();
        }
    }
    ```

    上面的和下面的XML文件等价：
    ```xml
    <beans>
        <bean id="myService" class="com.acme.services.MyServiceImpl"/>
    </beans>
    ```
- @Bean
  - 在方法上写此注释，和XML文件中的bean标签的作用一样，只不过格式有所变化
  - 格式如下：
  ```  
    @Bean
    public 接口/类名（对应XML中class值） 对象名（对应XML中id值） (){
        return new 类名（对应XML中class值）
    }
    
  ```
- @Component(Bean的实体类注解)
  - @Component是任何 Spring 管理的组件的通用构造型。
  - @Repository、@Service和@Controller是@Component针对更具体用例（分别在持久层、服务层和表示层）的特化。
  - 因此，您可以来注解你的组件类有 @Component，但是，通过与注解它们@Repository，@Service或者@Controller ，你的类能更好地适合于通过工具处理，或与切面进行关联。
  - @Repository, @Service, 和@Controller还可以在 Spring Framework 的未来版本中携带额外的语义。
  - 因此，如果您在使用之间进行选择@Component或者@Service对于你的服务层来说，@Service显然是更好的选择。
  - 同样，如前所述，@Repository已经支持作为持久层中自动异常转换的标记。
- @ComponentScan
    - 组件扫描注解，参数basePackages
    - `@ComponentScan(basePackages = "com.acme(包名)")`
    ```java
    @Configuration
    @ComponentScan(basePackages = "com.acme")
    public class AppConfig  {
    //...
    }
    ```
    相当于：
    ```xml
    <beans>
        <context:component-scan base-package="com.acme"/>
    </beans>
    ```
    

- 测试类中使用：(AnnotationConfigApplicationContext)
```java
public class MyTest {

    @Test
    public void test() {
        //参数可以使多个配置类用 "," 隔开
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        
        MyService myService = ctx.getBean(MyService.class);
        myService.doStuff();

    }


}
```
