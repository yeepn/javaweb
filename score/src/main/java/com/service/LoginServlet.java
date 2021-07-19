package com.service;

import com.kingo.Task.KingoaUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    String dbName="ScoreUser";
    String dbPwd="XyESzxx63BwchZwX";
    String password="";
    String sno="";
    String err="";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        password = request.getParameter("password");
        sno = request.getParameter("sno");
        String token="";
        String uuid="";
        String[] reString  = new String[0];
        try {
            reString = KingoaUtil.getToke("1", sno, password).split("&");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(reString[0].contains("ERROR:")){
            response.setCharacterEncoding("utf-8");
            //response.setHeader("refresh", "3;url=/login.jsp");
            response.setContentType("text/html");
            PrintWriter pWriter = response.getWriter();
            pWriter.write(reString[0]+"<br>");
            pWriter.write(sno+password);
            pWriter.write("如果没有跳转，请点击<a href='/login.jsp'>这里</a>");
            pWriter.flush();
            pWriter.close();
        }
        else {
            token = reString[0];
            uuid = reString[1];
            response.setCharacterEncoding("utf-8");
            response.setHeader("refresh", "3;url=/index.jsp?token="+token+"&uuid="+uuid+"&xnxq=202000"+"&sno="+sno);
            response.setContentType("text/html");
            PrintWriter pWriter = response.getWriter();
            if(save(sno,password)){
                pWriter.write("OK!");
            }
            else {
                pWriter.write(err);
            }
            pWriter.write("登录成功<br>");
            pWriter.write("请等待链接跳转");
            pWriter.flush();
            pWriter.close();
        }

    }
    private boolean save(String sno,String pwd){
        String Url = "jdbc:mysql://localhost/scoreuser";//参数参考MySql连接数据库常用参数及代码示例
        String name = "ScoreUser";//数据库用户名
        String psd = "XyESzxx63BwchZwX";//数据库密码
        String jdbcName = "com.mysql.jdbc.Driver";
        String sql = "insert into user values(?,?)";
        try {
            Class.forName(jdbcName);
            Connection con = DriverManager.getConnection(Url, name, psd);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, sno);
            pst.setString(2, pwd);
            pst.executeUpdate();
            return true;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            err=e.getMessage();
            return false;
        } catch (SQLException e) {//执行与数据库建立连接需要抛出SQL异常
            // TODO Auto-generated catch block
            err=e.getMessage();
            return false;
        }
    }
}
