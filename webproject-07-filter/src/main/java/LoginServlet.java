import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getParameter("username");
        if(username!=null&&username.equals("admin")){
            req.getSession().setAttribute(Constant.SESSION_NAME,req.getRequestedSessionId());
            resp.sendRedirect("/Main.jsp");
        }
        else
            resp.sendRedirect("/error.jsp");

    }
}
