package top.calivnhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Session的第二个测试：获取Attribute并输出到控制台和网页
 *
 * @author CalvinHaynes
 * @date 2021/09/05
 */
public class SessionDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //create 参数设置为 false ，如果与req关联的HttpSession存在的话，
        //getSession 会返回与当前req关联的HttpSession.
        //不存在的话，返回null
        //如果设置create参数为true的话，不存在req关联的session的话就会创建一个新的session
        HttpSession session = req.getSession(false);

        //获取Attribute
        String name = (String) session.getAttribute("name");

        System.out.println(name);

        resp.getWriter().write("name:" + name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
