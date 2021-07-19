import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class HelloWord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
一.servletContext上下文对象
ServletContext servletContext = this.getServletContext();
作用1：一下方法就是利用servlet来在servlet之间传递信息
servletContext.setAttribute();
servletContext.getAttribute()
作用2：获取初始化参数
 在web.xml配置：
     <context-param>
        <param-name>mysql</param-name>
        <param-value>jdbc</param-value>
    </context-param>
    就可以通过servletContext.getInitParameter("mysql")
作用3：请求转发(url不变化，由这个servlet帮忙请求目标地址，在转给req)
 servletContext.getRequestDispatcher("/gp").forward(req,resp);
作用4：读取属性文件
InputStream resourceAsStream = this.getServletContext().getResourceAsStream();
Properties properties = new Properties();
properties.load(resourceAsStream);
properties.get()
二.   HttpServletRequest
     HttpServletResponse




    */

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("Hello Word");
    }
}
