<%@ page import="com.yeep.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: yeyeyeping
  Date: 2021-07-18
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    hello
<%
    User msg = (User) pageContext.getRequest().getAttribute("msg");
    out.print(msg.id+"</br>");
    out.print(msg.username+"</br>");
    out.println(msg.pwd+"</br>");
%>
</body>
</html>
