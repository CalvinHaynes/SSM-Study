package top.calvinhaynes.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("dog")
public class Dog {

    @Value("Belly")
    public String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
