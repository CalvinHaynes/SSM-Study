package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转发和重定向
 *
 * 通过SpringMVC来实现转发和重定向 - 无需视图解析器
 * 测试前，需要将视图解析器注释掉
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class ForwardAndRedirect {

    @RequestMapping("/far/t1")
    public String test1() {

        //转发：方式一
        return "/WEB-INF/jsp/hello.jsp";
    }

    @RequestMapping("/far/t2")
    public String test2() {

        //转发：方式二
        return "forward:/WEB-INF/jsp/hello.jsp";
    }

    @RequestMapping("/far/t3")
    public String test3() {
        //重定向
        return "redirect:/index.jsp";
    }
}
