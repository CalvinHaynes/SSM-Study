package top.calvinhaynes.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 博客
 * The type Blog.
 *
 * @author CalvinHaynes
 * @date 2021/08/22
 */
@Data
public class Blog {
    private int id;
    private String title;
    private String author;

    /** 属性名和字段名不一致,下划线命名和驼峰命名在配置文件中设置自动转换 */
    private Date createTime;
    private int views;
}
