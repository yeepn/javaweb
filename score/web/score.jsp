<%@ page import="com.kingo.Task.KingoaUtil" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.JsonParser" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonElement" %><%--
  Created by IntelliJ IDEA.
  User: Hasee
  Date: 2021/7/16
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String xqxn = request.getParameter("xnxq");
    String sno = request.getParameter("sno");
    String token = request.getParameter("token");
    String uuid = request.getParameter("uuid");
    String ret="{[]}";
    try {
        ret = KingoaUtil.getGrades("20200","1912080087" , "10475","2oisok2333th2045ik1zila41wj9ug21w9dn1nmy8q2ppq7f22hs2r24vgye1x4vmo1wjg0t1zimtm" ,"b3391597916445056457");
    } catch (Exception e) {
        e.printStackTrace();
    }

    JsonObject jsonObject= JsonParser.parseString(ret).getAsJsonObject();
    JsonArray jsonArray=jsonObject.getAsJsonArray("xscj");
%>
<html>
<head>
    <title>成绩结果</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/css/mdui.min.css"
            integrity="sha384-cLRrMq39HOZdvE0j6yBojO4+1PrHfB7a9l5qLcmRm/fiWXYY+CndJPmyu5FV/9Tw"
            crossorigin="anonymous"
    />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>

<body class="mdui-theme-primary-indigo mdui-theme-accent-pink mdui-theme-layout-auto mdui-loaded" style="margin: 20px">
<%
    for (JsonElement obj : jsonArray) {
%>
<div class="mdui-card">
    <div class="mdui-card-media">
        <img src="card.jpg"/>
        <div class="mdui-card-menu">
            <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i>
            </button>
        </div>
    </div>
    <div class="mdui-card-primary">
        <div class="mdui-card-primary-title" style="font-size: 3rem"><% out.print(obj.getAsJsonObject().get("kcmc").getAsString());%> <span
                style="color: red;font-size: 5rem">100</span></div>
        <div class="mdui-card-primary-subtitle" style="font-size: 2rem"><br>公共基础平台课/必修课</div>
    </div>
    <div class="mdui-card-content">
        课程代码：0086<br>
        修读性质：初修<br>
        辅助标记：主修<br>
        考试成绩：主修<br>
        平时成绩：主修<br>
        期末成绩：主修<br>

        学分：1
    </div>

</div>
<%
        }
%>

<script
        src="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/js/mdui.min.js"
        integrity="sha384-gCMZcshYKOGRX9r6wbDrvF+TcCCswSHFucUzUPwka+Gr+uHgjlYvkABr95TCOz3A"
        crossorigin="anonymous"
></script>
</body>