package top.calvinhaynes.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import top.calvinhaynes.pojo.Books;

import java.util.List;

/**
 * 书服务
 *
 * @author CalvinHaynes
 * @date 2021/09/08
 */
public interface BookService {

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
    int deleteBookById(int id);

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
    Books selectBookById(int id);

    /**
     * 查询所有的书
     *
     * @return the list
     */
    List<Books> selectAllBooks();

    /**
     * 按名字查询对应的书籍
     *
     * @param name 的名字
     * @return {@link Books}
     */
    Books queryBookByName(String name);

}
