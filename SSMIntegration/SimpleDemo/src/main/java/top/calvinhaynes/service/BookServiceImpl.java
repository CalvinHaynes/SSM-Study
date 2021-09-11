package top.calvinhaynes.service;

import top.calvinhaynes.dao.BookMapper;
import top.calvinhaynes.pojo.Books;

import java.util.List;

/**
 * Service层调Dao层
 *
 * @author CalvinHaynes
 * @date 2021/09/08
 */
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 增加一本书
     *
     * @param book the book
     * @return the int
     */
    @Override
    public int addBook(Books book) {
         return bookMapper.addBook(book);
    }

    /**
     * 按ID删除一本书
     *
     * @param id the id
     * @return the int
     */
    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    /**
     * 更新一本书
     *
     * @param books the books
     * @return the int
     */
    @Override
    public int updateBookById(Books books) {
        return bookMapper.updateBookById(books);
    }

    /**
     * 根据ID查询一本书
     *
     * @param id the id
     * @return the books
     */
    @Override
    public Books selectBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    /**
     * 查询所有的书
     *
     * @return the list
     */
    @Override
    public List<Books> selectAllBooks() {
        return bookMapper.selectAllBooks();
    }

    /**
     * 按名字查询对应的书籍
     *
     * @param name 的名字
     * @return {@link Books}
     */
    @Override
    public Books queryBookByName(String name) {
        return bookMapper.queryBookByName(name);
    }
}
