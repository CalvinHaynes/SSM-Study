package top.calvinhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获得资源
 *
 * @author CalvinHaynes
 * @date 2021/08/28
 */
public class ObtainResource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        InputStream resource = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");

        Properties prop = new Properties();
        prop.load(resource);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.getWriter().print("username:" + username + "</br>" + "password:" + password);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
