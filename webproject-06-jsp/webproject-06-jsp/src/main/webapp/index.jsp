<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>

<body>
<form action="index.jsp" method="get">
    <input type="text"name="username" value="${param.username}">
    <input type="submit">
</form>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="管理员欢迎你"></c:out>
</c:if>
</body>
</html>
