<%--
  Created by IntelliJ IDEA.
  User: yeyeyeping
  Date: 2021-07-13
  Time: 7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="${pageContext.request.getContextPath()}/login">
    用户名：<input type="text" name="username"><br>
    密码:<input type="password" name="password" id=""><br>
    <input type="submit">
  </form>
  </body>
</html>
