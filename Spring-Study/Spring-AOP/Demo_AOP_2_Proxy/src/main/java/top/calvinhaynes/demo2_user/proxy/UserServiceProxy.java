package top.calvinhaynes.demo2_user.proxy;

import top.calvinhaynes.demo2_user.real_subject.UserServiceImpl;
import top.calvinhaynes.demo2_user.subject.UserService;

/**
 * @ProjectName: UserServiceProxy
 * @Author: CalvinHaynes
 * @Date: 2021/8/9 16:51
 * @Description:用户服务代理类，增加日志功能
 */
public class UserServiceProxy implements UserService {

    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void addUser() {
        log("[Log] add");
        userService.addUser();
    }

    @Override
    public void deleteUser() {
        log("[Log] delete");
        userService.deleteUser();
    }

    @Override
    public void updateUser() {
        log("[Log] update");
        userService.updateUser();
    }

    @Override
    public void queryUser() {
        log("[Log] query");
        userService.queryUser();
    }

    //增加一个打印日志的功能,其实是 AOP 的一个小实现，不修改原有业务代码的前提下新增功能
    public void log(String message){
        System.out.println(message);
    }
}
