package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试SpringMVC的过滤器的Controller
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
@Controller
public class EncodingController {

    @PostMapping("/e/t")
    public String test1(Model model, String name){
        //获取表单提交的值
        model.addAttribute("msg",name);
        //跳转到hello页面显示输入的值
        return "hello";
    }
}
