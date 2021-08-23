package top.calvinhaynes.dao;

import top.calvinhaynes.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * 博客映射器
 *
 * @author CalvinHaynes
 * @date 2021 /08/22
 */
public interface BlogMapper {
    /**
     * Query blog if
     *
     * @param map the map
     * @return the blog
     */
    Blog queryBlogIf(Map<String, String> map);

    /**
     * Query blog choose blog.
     *
     * @param map the map
     * @return the blog
     */
    Blog queryBlogChoose(Map<String, String> map);

    /**
     * Update blog int.
     *
     * @param map the map
     * @return the int
     */
    int updateBlogSet(Map<String, Object> map);

    /**
     * 查询博客foreach
     *
     * @param map 地图
     * @return {@link List}<{@link Blog}>
     */
    List<Blog> queryBlogForeach(Map<String, Object> map);

}
