package top.calvinhaynes.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书
 *
 * @author CalvinHaynes
 * @date 2021/09/08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    private int bookId;
    private String bookName;
    private int bookCounts;
    private String detail;

}
