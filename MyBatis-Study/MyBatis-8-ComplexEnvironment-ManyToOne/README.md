# MyBatis-8-ComplexEnvironment-ManyToOne

## 前言

复杂的数据库环境中多对一的处理

## 复杂环境搭建

### 1 - 执行SQL创建两个关联的数据库，多个学生对应一个老师

```sql
CREATE TABLE `teacher` (
                           `id` INT(10) NOT NULL,
                           `name` VARCHAR(30) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO teacher(`id`, `name`) VALUES (1, '大司马');

CREATE TABLE `student` (
                           `id` INT(10) NOT NULL,
                           `name` VARCHAR(30) DEFAULT NULL,
                           `tid` INT(10) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fktid` (`tid`),
                           CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `student` (`id`, `name`, `tid`) VALUES (1, '小明', 1);
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (2, '小红', 1);
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (3, '小张', 1);
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (4, '小李', 1);
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (5, '小王', 1);
```

### 2 - 使用Lombok，导包

```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>
```

### 3 - 实体类创建

- Student.java

```java
@Data
public class Student {
    private int id;
    private String name;

    private Teacher teacher;
}
```

- Teacher.java

```java
@Data
public class Teacher {
    private int id;
    private String name;
}
```

### 4 - 创建Mapper接口的Mapper.xml配置文件

- StudentMapper.java

```java
/**
 * 学生映射器
 * The interface Student mapper.
 *
 * @author CalvinHaynes
 * @date 2021 /08/20
 */
public interface StudentMapper {

    /**
     * Gets student full info.
     * 得到学生的完整信息，包括他们的老师（方式一）
     *
     * @return the student full info
     */
    List<Student> getStudentFullInfo();


    /**
     * Gets student full info 2.
     * 得到学生的完整信息（方式二）
     *
     * @return the student full info 2
     */
    List<Student> getStudentFullInfo2();
}
```

- StudentMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.calvinhaynes.mapper.StudentMapper">
    <!--查询学生完整信息有两种思路：
        第一种：按照查询嵌套处理（MySQL子查询）
        第二种：按照结果嵌套处理（MySQL联表查询）
    -->

    <!--方式一：查询嵌套-->
    <!--主要思路：
        1.查询所有学生信息
        2.根据查询出来的学生的tid查询特定的老师（子查询）(结果集映射）
    -->
    <select id="getStudentFullInfo" resultMap="studentToTeacher">
        select *
        from student;
    </select>
    <!--子查询-->
    <resultMap id="studentToTeacher" type="Student">
        <!--property：类属性名  column：数据库字段名 -->
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <!--多对一映射用association标签-->
        <!--select：SQL子查询 javaType：对应property的类型-->
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher">
            <!--Teacher的属性id和数据库中字段名tid映射-->
            <result property="id" column="tid"/>
        </association>
    </resultMap>
    <select id="getTeacher" resultType="Teacher">
        select *
        from teacher
        where id = #{tid};
    </select>

    <!--=========================================================================================-->
    <!--方式二：结果嵌套(联表查询)-->
    <!--思路：
        1.写好查询信息的完整SQL语句
        2.根据SQL语句的各个查询结果利用resultMap说明查询结果和数据库中数据名的一一映射关系
    -->
    <select id="getStudentFullInfo2" resultMap="studentToTeacher2">
        select s.id sid, s.name sname, t.id, t.name tname
        from student s,
             teacher t
        where t.id = s.tid;
    </select>
    <!--对于查询需要得到的各个结果的一一映射关系说明-->
    <!--首先得到的结果一定是个Student，Student中有id，name和teacher的各个信息-->
    <resultMap id="studentToTeacher2" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <!--Teacher的结果首先类型肯定是个Teacher类，然后信息有id和name-->
        <association property="teacher" javaType="Teacher">
            <result property="id" column="id"/>
            <result property="name" column="tname"/>
        </association>
    </resultMap>
</mapper>
```

### 5 - MyBatis配置文件中绑定Mapper

```xml
<mappers>
    <package name="top.calvinhaynes.mapper"/>
</mappers>
```

### 6 - 测试

```java
/**
 * Many to one query test.
 *
 * 方式一：按照查询嵌套处理 测试
 */
@Test
public void ManyToOneQueryTest(){
    SqlSession session = MyBatisUtils.getSession();

    top.calvinhaynes.mapper.StudentMapper mapper = session.getMapper(top.calvinhaynes.mapper.StudentMapper.class);

    List<Student> studentList = mapper.getStudentFullInfo();

    for (Student student : studentList) {
        System.out.println(student);
    }

    session.close();
}

/**
 * Many to one query test 2.
 * 
 * 方式二：按照结果嵌套处理 测试
 */
@Test
public void ManyToOneQueryTest2(){
    SqlSession session = MyBatisUtils.getSession();

    top.calvinhaynes.mapper.StudentMapper mapper = session.getMapper(top.calvinhaynes.mapper.StudentMapper.class);

    List<Student> studentList = mapper.getStudentFullInfo2();

    for (Student student : studentList) {
        System.out.println(student);
    }

    session.close();
}
```



