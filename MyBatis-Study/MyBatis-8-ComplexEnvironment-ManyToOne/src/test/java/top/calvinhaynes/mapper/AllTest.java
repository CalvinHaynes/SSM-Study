package top.calvinhaynes.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.pojo.Student;
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
     * 环境测试
     */
    @Test
    public void environmentTest(){
        SqlSession session = MyBatisUtils.getSession();

        top.calvinhaynes.mapper.TeacherMapper mapper = session.getMapper(top.calvinhaynes.mapper.TeacherMapper.class);

        List<Teacher> teacherList = mapper.getTeacher();

        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }

        session.close();
    }

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
}
