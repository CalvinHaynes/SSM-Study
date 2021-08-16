package top.calvinhaynes.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Person {
    public String name = "Calvin Haynes";
    @Autowired
    public Dog dog;


}
