package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    String hello(Model model){
        model.addAttribute("msg","HelloSpringMVC,now I use Annotation!");

        return "hello";
    }
}
