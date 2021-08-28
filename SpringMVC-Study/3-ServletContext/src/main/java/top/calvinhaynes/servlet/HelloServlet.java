package top.calvinhaynes.servlet;



import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 向ServletContext中写数据
 * http://localhost:8080//servletcontext
 *
 * @author CalvinHaynes
 * @date 2021/08/25
 */
public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //测着玩
        System.out.println(resp.getStatus());
        System.out.println(this.getServletInfo());
        System.out.println(this.getServletConfig());
        System.out.println(this.getServletName());

        ServletContext context = this.getServletContext();

        String username = "Calvin Haynes";
        context.setAttribute("username", username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
