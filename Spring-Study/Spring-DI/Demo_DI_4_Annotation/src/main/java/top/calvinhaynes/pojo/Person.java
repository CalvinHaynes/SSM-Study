package top.calvinhaynes.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
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
