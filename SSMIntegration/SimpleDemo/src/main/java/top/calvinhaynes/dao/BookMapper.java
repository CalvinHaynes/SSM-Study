package top.calvinhaynes.dao;

import org.apache.ibatis.annotations.Param;
import top.calvinhaynes.pojo.Books;

import java.util.List;

/**
 * 书映射器
 *
 * @author CalvinHaynes
 * @date 2021 /09/08
 */
public interface BookMapper {

    /**
     * 增加一本书
     *
     * @param book the book
     * @return the int
     */
    int addBook(Books book);

    /**
     * 按ID删除一本书
     *
     * @param id the id
     * @return the int
     */
    int deleteBookById(@Param("bookId") int id);

    /**
     * 更新一本书
     *
     * @param books the books
     * @return the int
     */
    int updateBookById(Books books);

    /**
     * 根据ID查询一本书
     *
     * @param id the id
     * @return the books
     */
    Books selectBookById(@Param("bookId") int id);

    /**
     * 查询所有的书
     *
     * @return the list
     */
    List<Books> selectAllBooks();

    /**
     * 查询书的名字
     * 按名字查询对应的书
     *
     * @param name 名字
     * @return {@link Books}
     */
    Books queryBookByName(@Param("bookName") String name);
}
