import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String attribute = (String)req.getSession().getAttribute(Constant.SESSION_NAME);
        if(attribute==null){
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            resp.sendRedirect("/error.jsp");
        }
        filterChain.doFilter(req,resp);

    }

    public void destroy() {

    }
}
