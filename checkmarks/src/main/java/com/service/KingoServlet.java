package com.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.kingo.Task.KingoaUtil;

@WebServlet("/checkmarks")
public class KingoServlet extends HttpServlet {

	private String token;
	private String uuid;
	
	public KingoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	private String formatStr(String t) {
		t = t.substring(8,t.length()-1);
        Gson gson = new Gson();
        List list = gson.fromJson(t,List.class);
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(x->{
            String s = x.toString();
            s = s.replace("=","\":\"");
            s = s.replace(", ","\",\"");
            s = s.replace("{","{\"");
            s =  s.replace("}","\"}");
            Map<String, String> map = gson.fromJson(s.toString(), Map.class);
            stringBuilder.append(String.format("[%s]%s        %s        %s        %s",map.get("kcdm").toString(),map.get("kcmc").toString(),map.get("kscj").toString(),map.get("xf").toString(),map.get("kclb").toString()+"\n"));
        });
    return stringBuilder.toString();
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String xqxn = req.getParameter("xnxq");
		String sno = req.getParameter("sno");
		try {
			String ret = KingoaUtil.getGrades(xqxn,sno , "10475",token ,uuid );
			while (ret.contains("非法访问，签名有误")) {
				ret  = KingoaUtil.getGrades(xqxn,sno , "10475",token ,uuid );
			}
			if (ret.contains("xf")) {
				
				ret = formatStr(ret);
			}
			else if (ret.contains("口令失败")) {
				String[] reString  = KingoaUtil.getToke("1", "2", "3").split("&");
				this.token = reString[0];
				this.uuid = reString[1];
				ret  = KingoaUtil.getGrades(xqxn,sno , "10475",token ,uuid );
			}
			
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			PrintWriter pWriter = resp.getWriter();
			pWriter.write(ret);
			pWriter.flush();
			pWriter.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	@Override
	public void init() throws ServletException {
		String[] reString;
		try {
			reString = (KingoaUtil.getToke("1", "2", "3")).split("&");
			this.token = reString[0];
			this.uuid = reString[1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
