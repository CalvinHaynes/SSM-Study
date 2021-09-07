package top.calvinhaynes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.calvinhaynes.pojo.User;

/**
 * 数据处理
 *
 * @author CalvinHaynes
 * @date 2021/09/07
 */
@Controller
public class DataProcessing {

    /**
     * 提交的域名称和处理方法的参数名一致
     *
     * @param name 的名字
     * @return {@link String}
     */
    @RequestMapping("/hello2")
    public String hello2(String name) {
        System.out.println(name);
        return "hello";
    }

    /**
     * 提交的域名称和处理方法的参数名不一致
     *
     * @param name 的名字
     * @return {@link String}
     */
    @RequestMapping("/hello3")
    public String hello3(@RequestParam("username") String name) {
        System.out.println(name);
        return "hello";
    }

    /**
     * 用户
     *
     * @param user 用户
     * @return {@link String}
     */
    @RequestMapping("/user")
    public String user(User user){
        System.out.println(user);
        return "hello";
    }
}
