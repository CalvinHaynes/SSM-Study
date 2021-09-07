package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据显示到前端
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
@Controller
public class DataPrinting {

    /**
     * 通过ModelMap
     *
     * @return {@link String}
     */
    @RequestMapping("/hello4")
    public String hello4(@RequestParam("username") String name, ModelMap model) {
        //封装数据
        model.addAttribute("msg", name);

        System.out.println(name);

        return "hello";
    }

    /**
     * 通过Model
     *
     * @return {@link String}
     */
    @RequestMapping("/hello5")
    public String hello5(@RequestParam("username") String name, Model model) {
        //封装数据
        model.addAttribute("msg", name);

        System.out.println(name);

        return "hello";
    }

    /**
     * 通过ModelAndView
     *
     * @return {@link String}
     */
    @RequestMapping("/hello6")
    public ModelAndView hello6(@RequestParam("username") String name) {
        //封装数据
        System.out.println(name);

        return new ModelAndView("hello", "msg", name);
    }
}
