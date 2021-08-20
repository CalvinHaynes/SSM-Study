package top.calvinhaynes.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.calvinhaynes.pojo.Teacher;

import java.util.List;

/**
 * 老师映射器
 * The interface Teacher mapper.
 *
 * @author CalvinHaynes
 * @date 2021 /08/20
 */
public interface TeacherMapper {

    /**
     * Gets teacher.
     * 测试环境的方法
     *
     * @return the teacher
     */
    @Select("select * from teacher")
    List<Teacher> getAllTeacher();

    /**
     * Gets teacher full info by id
     * 获得指定id的老师所有的信息，包括一个老师对应的多个学生的信息
     *
     * @param id the id
     * @return the teacher full info
     */
    List<Teacher> getTeacherFullInfoById(@Param("tid") int id);
}
