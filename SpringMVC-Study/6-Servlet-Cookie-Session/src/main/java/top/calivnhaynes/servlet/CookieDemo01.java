package top.calivnhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Cookie 第一个测试：在Cookie中存入上一次访问网站的时间
 *
 * @author CalvinHaynes
 * @date 2021/09/04
 */
public class CookieDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();

        //服务器端从客户端获取Cookie
        Cookie[] cookies = req.getCookies();

        //判断Cookie是否存在
        if (cookies != null) {
            //存在cookie进行的测试：打印上一次访问此站的时间

            out.write("您上一次访问此站的时间是：");

            for (Cookie cookie : cookies) {
                //判断Cookie名字非空拿到它的值并打印
                if ("lastLoginTime".equals(cookie.getName())) {
                    //字符串转为日期打印到网页上
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }
            }
        } else {
            out.write("这是您第一次访问本站");
        }

        //创建Cookie,名字是lastLoginTime，值是系统当前时间
        Cookie lastLoginTime = new Cookie("lastLoginTime", System.currentTimeMillis()+"");

        //设置Cookie的存在时限，单位秒
        lastLoginTime.setMaxAge(24 * 60 * 60);

        //服务器向客户端响应Cookie
        //其实只有第一次访问的时候执行了
        resp.addCookie(lastLoginTime);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
