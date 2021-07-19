<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>你好，你的sessionid为
<%
out.println(request.getRequestedSessionId());
%>

</h1>
</body>
</html>
