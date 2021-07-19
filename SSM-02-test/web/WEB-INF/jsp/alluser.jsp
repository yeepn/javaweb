<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<c:forEach var="item" items="${msg}" >
    <h1>${item.id}</h1>
    <h2>${item.username}</h2>
    <h3>${item.pwd}</h3>
</c:forEach>
<body>
</body>
</html>
