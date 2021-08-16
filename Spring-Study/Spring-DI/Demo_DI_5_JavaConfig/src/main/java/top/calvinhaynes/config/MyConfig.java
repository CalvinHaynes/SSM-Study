package top.calvinhaynes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.calvinhaynes.pojo.Person;


@Configuration//配置类注解
public class MyConfig {

    //通过方法注册一个bean，这里的返回值就Bean的类型，方法名就是bean的id！
    //相当于 XML 文件中的 <bean id="person" class="top.calvinhaynes.pojo.Person"/>
    @Bean
    public Person person() {
        return new Person();
    }
}
