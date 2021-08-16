package top.calvinhaynes;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.calvinhaynes.pojo.Student;

public class MyTest {

    //基本类型值 DI
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student0 = (Student) context.getBean("student0");
        System.out.println(student0.getName());
    }

    //自定义类型值 DI
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student1 = (Student) context.getBean("student1");
        System.out.println(student1.getName());
        System.out.println(student1.getAddress().getAddress());
    }

    //数组类型 DI
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student2 = (Student) context.getBean("student2");
        student2.showBooks();
    }

    //List 类型 DI
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student3 = (Student) context.getBean("student3");
        student3.showHobbys();

    }

    //Map 类型 DI
    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student4 = (Student) context.getBean("student4");
        student4.showCards();
    }

    //Set DI
    @Test
    public void test6() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student5 = (Student) context.getBean("student5");
        student5.showGames();
    }

    //Null DI
    @Test
    public void test7() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student6 = (Student) context.getBean("goodStudent");
        student6.showWife();
    }

    //Property DI
    @Test
    public void test8(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student7 = (Student) context.getBean("lastStudent");
        student7.showInfo();
    }

    //综合测试
    @Test
    public void test9() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student wholeStudent = (Student) context.getBean("wholeStudent");
        wholeStudent.show();
    }

}
