import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sendRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(req.getContextPath());
        //注意点是 根目录是web的目录，以及pagecontext.req.getContextPath获取的是web应用的路径
        if(username.equals("admin")&&password.equals("123"))
            resp.sendRedirect("/success.jsp");
        else
            resp.sendRedirect("/fail.jsp");

    }
}
