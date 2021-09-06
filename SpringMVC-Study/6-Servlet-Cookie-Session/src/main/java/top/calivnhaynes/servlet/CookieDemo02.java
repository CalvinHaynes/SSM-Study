package top.calivnhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author CalvinHaynes
 * @date 2021/09/05
 */
public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        //解决网页中文显示乱码
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        //服务器端从客户端获取Cookie
        Cookie[] cookies = req.getCookies();

        //判断Cookie是否存在
        if (cookies != null) {
            //存在cookie进行的测试：打印上一次访问此站的时间

            out.write("本站的创始人是：");

            for (Cookie cookie : cookies) {
                //判断Cookie名字非空拿到它的值并打印
                if ("name".equals(cookie.getName())) {
                    System.out.println(URLDecoder.decode(cookie.getValue(), "GBK"));
                    out.write(URLDecoder.decode(cookie.getValue(), "GBK"));
                }
            }
        } else {
            out.write("这是您第一次访问本站");
        }

        //创建Cookie
        Cookie name = new Cookie("name", URLEncoder.encode("马丁", "GBK"));

        //服务器响应给客户端Cookie
        resp.addCookie(name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
