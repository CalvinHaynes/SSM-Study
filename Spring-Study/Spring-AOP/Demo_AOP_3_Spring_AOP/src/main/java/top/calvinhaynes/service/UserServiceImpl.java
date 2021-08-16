package top.calvinhaynes.service;

/**
 * @ProjectName: UserServiceImpl
 * @Author: CalvinHaynes
 * @Date: 2021/8/15 10:03
 * @Description:用户服务接口实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("增加一个用户");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除一个用户");
    }

    @Override
    public void updateUser() {
        System.out.println("更新一个用户");
    }

    @Override
    public void queryUser() {
        System.out.println("查找一个用户");
    }
}
