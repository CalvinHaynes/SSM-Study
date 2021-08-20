package top.calvinhaynes.pojo;

import lombok.Data;

import java.util.List;

/**
 * 老师
 *
 * @author CalvinHaynes
 * @date 2021 /08/20
 */
@Data
public class Teacher {
    private int id;
    private String name;

    /** 一个老师拥有多个学生 */
    private List<Student> students;
}
