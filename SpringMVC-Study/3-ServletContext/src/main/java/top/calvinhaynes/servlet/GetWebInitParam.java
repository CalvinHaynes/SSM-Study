package top.calvinhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 获取web servlet init参数
 *
 * @author CalvinHaynes
 * @date 2021/08/28
 */
public class GetWebInitParam extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String encoding = this.getServletContext().getInitParameter("encoding");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.getWriter().println("encoding:" + encoding);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
