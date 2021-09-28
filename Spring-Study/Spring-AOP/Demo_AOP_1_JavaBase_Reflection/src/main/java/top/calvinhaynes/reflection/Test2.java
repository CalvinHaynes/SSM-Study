package top.calvinhaynes.reflection;

/**
 * test2:获取反射对象的四种方式
 *
 * @author CalvinHaynes
 * @date 2021/09/28
 */
public class Test2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是" + person.name);

        //方式一：通过对象获得
        Class<? extends Person> personClass = person.getClass();
        System.out.println(personClass.hashCode());

        //方式二：forName获得
        Class<?> personClass1 = Class.forName("top.calvinhaynes.reflection.Student");
        System.out.println(personClass1.hashCode());

        //方式三：通过类名.class获得
        Class<Student> studentClass = Student.class;
        System.out.println(studentClass.hashCode());

        //方式四：基本类型的包装类都有一个 Type属性
        Class<Integer> integerClass = Integer.TYPE;
        System.out.println(integerClass);

        //获得父类类型
        Class<? super Student> superclass = studentClass.getSuperclass();
        System.out.println(superclass);
    }

}


class Person{
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student() {
        this.name = "学生";
    }
}

class Teacher extends Person{
    public Teacher() {
        this.name = "老师";
    }
}