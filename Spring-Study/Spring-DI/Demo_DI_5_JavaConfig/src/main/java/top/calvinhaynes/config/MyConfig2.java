package top.calvinhaynes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.calvinhaynes.pojo.Dog;

@Configuration
@Import(MyConfig.class)//导入另一个配置类
public class MyConfig2 {

    @Bean
    public Dog dog() {
        return new Dog();
    }

}
