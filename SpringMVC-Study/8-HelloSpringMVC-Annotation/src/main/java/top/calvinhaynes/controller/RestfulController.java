package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 实行Restful风格的Controller
 *
 * @author CalvinHaynes
 * @date 2021/09/06
 */
@Controller
public class RestfulController {

//    @RequestMapping(value="/restful/{a}/{b}",method= RequestMethod.GET)

    @GetMapping("/restful/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable int b, Model model) {

        int result = a + b;

        model.addAttribute("msg", "RestfulController:result=" + result);

        return "rest";
    }
}
