package top.calivnhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 关于Session的第一个测试：
 *  - 在Session中存入一个name的属性
 *  - 打印Session的ID到网页上
 *
 * @author CalvinHaynes
 * @date 2021/09/05
 */
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();

        session.setAttribute("name", "Calvin Haynes");

        String sessionId = session.getId();

        if(session.isNew()){
            resp.getWriter().write("Session创建成功，ID=" + sessionId);
        }else{
            resp.getWriter().write("Session已经在服务器端存在，ID=" + sessionId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
