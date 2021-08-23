package top.calvinhaynes.mapper;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.calvinhaynes.dao.BlogMapper;
import top.calvinhaynes.pojo.Blog;
import top.calvinhaynes.utils.MyBatisUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有测试
 *
 * @author CalvinHaynes
 * @date 2021 /08/19
 */
public class AllTest {
    @Test
    public void queryBlogIfTest() {
        SqlSession session = MyBatisUtils.getSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Map<String, String> map = new HashMap<>();
        map.put("author", "James Gosling");
        map.put("title", "JavaBasics");
        Blog blog = mapper.queryBlogIf(map);

        System.out.println(blog);

        session.close();
    }

    @Test
    public void queryBlogChooseTest() {
        SqlSession session = MyBatisUtils.getSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Map<String, String> map = new HashMap<>();
        map.put("author", "James Gosling");
        map.put("title", "JavaBasics");
        Blog blog = mapper.queryBlogChoose(map);

        System.out.println(blog);

        session.close();
    }

    @Test
    public void updateBlogSetTest() {
        SqlSession session = MyBatisUtils.getSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("author", "Calvin Haynes");
        map.put("id", 20210823);

        int blog = mapper.updateBlogSet(map);

        if (blog > 0) {
            System.out.println("更新信息成功");
        }

        session.close();
    }

    @Test
    public void queryBlogForeachTest() {
        SqlSession session = MyBatisUtils.getSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Map<String, Object> map = new HashMap<>();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(20210622);
        ids.add(20210722);
        ids.add(20210822);
        map.put("ids", ids);

        List<Blog> blogs = mapper.queryBlogForeach(map);

        System.out.println(blogs);

        session.close();
    }

}
