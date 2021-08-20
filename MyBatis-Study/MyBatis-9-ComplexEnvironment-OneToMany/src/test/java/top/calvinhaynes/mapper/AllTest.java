package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Teacher;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.List;

/**
 * 所有测试
 *
 * @author CalvinHaynes
 * @date 2021 /08/19
 */
public class AllTest {

    /**
     * Get teacher full info test.
     */
    @Test
    public void getTeacherFullInfoTest(){
        SqlSession session = MyBatisUtils.getSession();

        TeacherMapper mapper = session.getMapper(TeacherMapper.class);

        List<Teacher> teacherFullInfo = mapper.getAllTeacher();
        for (Teacher teacher : teacherFullInfo) {
            System.out.println(teacher);
        }

        session.close();

    }

    /**
     * Get teacher full info by id test.
     */
    @Test
    public void getTeacherFullInfoByIdTest(){
        SqlSession session = MyBatisUtils.getSession();

        TeacherMapper mapper = session.getMapper(TeacherMapper.class);

        List<Teacher> teacherFullInfo = mapper.getTeacherFullInfoById(1);
        for (Teacher teacher : teacherFullInfo) {
            System.out.println(teacher);
        }

        session.close();

    }


}
