package com.yeep.servlet;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置浏览器的自动刷新
        resp.setHeader("refresh","3");
        //设置缓存策略
        resp.setDateHeader("Expries",-1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        //绘制图片
        //在内存中准备图片
        BufferedImage bufferedImage = new BufferedImage(200,100,BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,200,100);
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font(null,Font.BOLD,20));
        graphics.drawString(getNum(),80,40);
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());

    }
    public  String getNum(){
        Random random = new Random();
        String s = String.valueOf(random.nextInt(9999999));
        StringBuilder sb = new StringBuilder(s);
        for(int i=0;i<7-s.length();i++)
        {
            sb.append('0');
        }
        return sb.toString();
    }
}
