package top.calvinhaynes.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.Calendar;

public class Person {
    @Resource
    private Cat cat;
    @Autowired
    @Qualifier(value = "dog1")
    private Dog dog;
    @Autowired(required = false)
    private String name;

    public Cat getCat() {
        return cat;
    }

    public Person() {
    }

    public Person(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
