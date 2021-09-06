package top.calvinhaynes.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * 得到参数和请求转发
 *
 * @author CalvinHaynes
 * @date 2021/08/30
 */
public class GetParamAndRequestForwarding extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] languages = req.getParameterValues("languages");

        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(languages));

        //测试
        System.out.println(req.getContextPath());

        //请求转发,其中 success.jsp前面的 / 就代表当前项目目录了
        req.getRequestDispatcher("/success.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
