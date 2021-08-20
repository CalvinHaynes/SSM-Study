package top.calvinhaynes.mapper;

import org.apache.ibatis.annotations.Select;
import top.calvinhaynes.pojo.Teacher;

import java.util.List;

/**
 * 老师映射器
 * The interface Teacher mapper.
 *
 * @author CalvinHaynes
 * @date 2021/08/20
 */
public interface TeacherMapper {

    /**
     * Gets teacher.
     * 测试环境的方法
     * @return the teacher
     */
    @Select("select * from teacher")
    List<Teacher> getTeacher();
}
