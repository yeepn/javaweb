package com.kingo.Task;
import com.google.gson.Gson;
import com.kingo.EncryptHelper.FakeNDKEncrypt;
import java.util.*;

public class KingoaUtil {


    public KingoaUtil() {

    }

    public static String getToke(String xxdm ,String sno,String pwd) throws Exception {
        int tryTimes=0;
        xxdm = "10475";//学校代码
//        sno = "1904060087";
//        pwd = "yp010425";
        String s = "xxdm=" + xxdm + "&sjxh=Redmi Note 4&loginId=" + sno + "&sswl=&os=android&xtbb=&appver=2.6.304&action=getLoginInfoNew&isky=1&sjbz=&pwd=" + pwd + "&loginmode=0";
        HashMap map = new FakeNDKEncrypt(s, FakeNDKEncrypt.MODE.GETTOKE).doFinal_GetToken();
        Thread.sleep(500);
        String ret = (new Requests()).post("http://api.xiqueer.com/manager//wap/wapController.jsp ", map);
        String res = "";
        if (ret.contains("通过身份验证！")) {
            Map map2 = new Gson().fromJson(ret, Map.class);
            res = map2.get("token") + "&" + map2.get("uuid");
            return res;
        } else if (ret.contains("非法访问")) {
            tryTimes++;
            if(tryTimes>=10){
                return "ERROR:非法访问";
            }
            Thread.sleep(500);
            res = getToke(xxdm, sno, pwd);
            return res;
        } else {
            return "ERROR:未知错误\n"+ret;
        }


    }

    public static String getGrades(String xnxq,String sno,String schoolcode,String token,String uuid) throws Exception { 	
        String s = "flag=0&xnxq="+xnxq+"&action=getStucj&usertype=STU&step=detail&userId="+schoolcode+"_"+sno+"&sfid="+schoolcode+"_"+sno+"&uuid="+uuid;
        HashMap<String, String> map =  new  FakeNDKEncrypt(s, FakeNDKEncrypt.MODE.OPERATE).doFinal_Operate(token);
        String ret  = (new Requests()).post("http://api.xiqueer.com/manager//wap/wapController.jsp",map);
        if (ret.contains("非法访问，签名有误")) {
        	ret = getGrades(xnxq, sno, schoolcode, token, uuid);
		}
        
        return ret;
}
}
