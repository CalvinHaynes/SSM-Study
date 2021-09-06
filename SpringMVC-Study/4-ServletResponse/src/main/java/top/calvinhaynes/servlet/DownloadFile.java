package top.calvinhaynes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 利用ServletResponse实现下载文件
 *
 * @author CalvinHaynes
 * @date 2021/08/28
 */
public class DownloadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.要获取下载文件的路径
        String realPath = "D:\\OpenSource\\My Repository\\My Repository for Github\\SSM-Study\\SpringMVC-Study\\4-ServletResponse\\src\\main\\resources\\Servlet第一个Demo.md";
        System.out.println("下载文件的绝对路径：" + realPath);
        //2.下载的文件名是啥？
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置想办法让浏览器能够支持下载我们需要的东西,设置响应头参数
        //设置响应头参数Content-Disposition：attachment -> 作为附件下载，文件名 -> fileName，并且采用 UTF-8 编码
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6.获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
        //7. 将文件输入流读入到buffer缓冲区,使用输出流将缓冲区中的数据写入客户端中！
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
