package top.calvinhaynes.mapper;

import top.calvinhaynes.pojo.Student;

import java.util.List;

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
