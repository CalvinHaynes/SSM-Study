package top.calvinhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 重定向
 *
 * @author CalvinHaynes
 * @date 2021/08/29
 */
public class Redirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //拿到登录请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username + "=" + password);

        //重定向实现
        resp.sendRedirect("/servletresponse/success.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
