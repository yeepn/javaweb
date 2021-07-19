import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        //System.out.println(Thread.currentThread().getContextClassLoader().getResource(".").getPath());
        //这里必须加上\，整个web的根路径
        String filepath = servletContext.getRealPath("/index.jsp");
        //从绝对路径中提取文件名字
        String filename = filepath.substring(filepath.lastIndexOf("\\") + 1);
        //告诉浏览器文件将会以流的形式发送
        resp.setContentType("application/octet-stream");
        //设置下载文件的协议头
        resp.addHeader("Content-Disposition","attachment;filename="+filename);
        ServletOutputStream outputStream = resp.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[2048];
        FileInputStream fileInputStream = new FileInputStream(filepath);
        while ((len=fileInputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        outputStream.flush();
        outputStream.close();
        fileInputStream.close();
    }
}
