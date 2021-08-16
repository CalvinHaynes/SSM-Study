# DEMO_IOC_1

Spring IOC思想学习的前导，最经典的业务结构编写

分两层：Dao，Service

Dao层有UserDao接口和实现不同功能的各种实现类

Service层有UserService接口和UserServiceImpl实现类，
实现类中由于UserDao的实现类创建的对象的不同也能为外界提供实现不同的功能的服务


```java
public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoMySQLImpl();

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```
这行代码就是UserServiceImpl实现类中创建的UserDao实现类的对象，
通过**修改不同的实现类**能够创建不同的实现类对象，从而达到调用不同的实现类中
的方法的目的

在MyTest测试类中的main方法中就可以实现UserDao的各种不同实现类中定义的方法
```java
public class MyTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.getUser();

    }
}

```

但是经过思考不难明白，当项目代码量巨大的时候，可能牵一发而动全身，修改业务代码变得非常困难，所以这正是
IOC 控制反转思想的优势，IOC 控制反转将在下个Demo中有所体现。

