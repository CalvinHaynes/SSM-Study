package top.calvinhaynes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.calvinhaynes.pojo.Books;
import top.calvinhaynes.service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * 本控制器
 *
 * @author CalvinHaynes
 * @date 2021/09/08
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.selectAllBooks();
        model.addAttribute("list", list);
        return "allBook";
    }

    @GetMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    @GetMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.selectBookById(id);
        System.out.println(books);
        model.addAttribute("book", books);
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(Model model, Books book) {
        System.out.println(book);
        bookService.updateBookById(book);
        Books books = bookService.selectBookById(book.getBookId());
        model.addAttribute("books", books);
        return "redirect:/book/allBook";
    }

    @GetMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    @PostMapping("/queryBookByName")
    public String queryBookByName(String bookName, Model model) {
        Books book = bookService.queryBookByName(bookName);

        List<Books> bookList = new ArrayList<>();

        if (book == null) {
            bookList = bookService.selectAllBooks();
            model.addAttribute("error", "It can't be null");
        } else {
            bookList.add(book);
        }

        model.addAttribute("list", bookList);
        return "allBook";
    }
}
