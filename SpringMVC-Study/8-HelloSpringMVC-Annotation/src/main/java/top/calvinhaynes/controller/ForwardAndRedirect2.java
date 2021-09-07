package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通过SpringMVC来实现转发和重定向 - 有视图解析器；
 * 可以重定向到另外一个请求实现 .
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class ForwardAndRedirect2 {

    @RequestMapping("/far2/t1")
    public String test1() {
        //转发
        return "hello";
    }

    @RequestMapping("/far2/t2")
    public String test2() {
        //重定向
//        return "redirect:/index.jsp";

        //重定向到另一个请求
        return "redirect:/restful/5/5";
    }
}
