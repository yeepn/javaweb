#### 1.什么是jsp？

jsp本质上就是一个servlet，在用户第一次访问jsp页面的时候，jsp容器会将该jsp转为java源代码，然后将文件预编译成为class文件，然后调用转换后的jsp的init方法，**这个方法在整个servlet生命周期只会调用一次**，由于jsp转换后的源码继承自httpbase，httpbase又继承自httpservlet，所以对于客户端发来的请求还是交给_jspService运行。

```
public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {
//这是一串代码块，用于过滤请求方法，JSP 只允许 GET、POST 或 HEAD。Jasper 还允许 OPTIONS,不重要省略了


    final PageContext pageContext;
    HttpSession session = null;
    final ServletContext application;
    final ServletConfig config;
    JspWriter out = null;
    final Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
        response.setContentType("text/html;charset=UTF-8");
        pageContext = _jspxFactory.getPageContext(this, request, response,
                null, true, 8192, true);
        _jspx_page_context = pageContext;
        application = pageContext.getServletContext();
        config = pageContext.getServletConfig();
        session = pageContext.getSession();
        out = pageContext.getOut();
        _jspx_out = out;

        out.write("\n");
        out.write("\n");
        out.write("<html>\n");
        out.write("  <head>\n");
        out.write("    <title>$Title$</title>\n");
        out.write("  </head>\n");
        out.write("  <body>\n");
        out.write("  <form action=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.getContextPath()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
        out.write("/login\">\n");
        out.write("    用户名：<input type=\"text\" name=\"username\"><br>\n");
        out.write("    密码:<input type=\"password\" name=\"password\" id=\"\"><br>\n");
        out.write("    <input type=\"submit\">\n");
        out.write("  </form>\n");
        out.write("  </body>\n");
        out.write("</html>\n");
    } catch (java.lang.Throwable t) {
        if (!(t instanceof javax.servlet.jsp.SkipPageException)){
            out = _jspx_out;
            if (out != null && out.getBufferSize() != 0)
                try {
                    if (response.isCommitted()) {
                        out.flush();
                    } else {
                        out.clearBuffer();
                    }
                } catch (java.io.IOException e) {}
            if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
            else throw new ServletException(t);
        }
    } finally {
        _jspxFactory.releasePageContext(_jspx_page_context);
    }
}
```

jsp中的重要对象,都可以在jsp页面中直接使用

```java final PageContext pageContext;
    HttpSession session = null;
    final ServletContext application;
    final ServletConfig config;
    JspWriter out = null;
    final Object page = this;
	final javax.servlet.http.HttpServletRequest request, 
	final javax.servlet.http.HttpServletResponse response
```

### 2.jsp的基本语法

导包：jstl

JavaServer Pages(TM) Standard Tag Library

这个包就是一个标签库，用于在写jsp的时候减少java代码，他的语法中是完全支持java代码的

1.<% %>中间写java代码

```jsp
<%out.println(new SimpleDateFormat().format(new Date().getTime()));%>
```

2.<%=java代码%>这个标签用于输出输出变量的值

```jsp
<%=new SimpleDateFormat().format(new Date().getTime())%>
```

3.全局申明

```java
<%!
public  static void println()
{
    System.out.println("1234");
}
%>
<%
println();
%>

```

4.${}

### 3.jsp指令

1.@page指令、@include提取公共部分

```jsp
<%@ page args %>
```

(2)自定义错误页面：该页面有代码错误的时候会自动导航到index.jsp

```java
<%@page errorPage="index.jsp"%>
```

也可以在web.xml中修改

```xml
  <error-page>
    <error-code>404</error-code>
    <location>/error/404.jsp</location>
  </error-page>
  <error-page>
    <error-code>500</error-code>
    <location>/error/500.jsp</location>
  </error-page>
```

```jsp

```



2.导包

```jsp
<%@ page import="java.util.Date" %>
```

3.jsp标签

![image-20210713101136809](C:\Users\yeyeyeping\AppData\Roaming\Typora\typora-user-images\image-20210713101136809.png)

4.el表达式去变量的值，最常用

${变量名字}

### 4.9大内置对象

#### ·PageContext

#### ·Response

#### ·Request

#### ·Session

#### ·Application[ServletContext]

·config

·out

·page

·exception

存放数据：

```java
 pageContext.setAttribute("name",123,PageContext.PAGE_SCOPE);//存的东西只在一个页面有效    //request.setAttribute();存的东西只在一次请求有效
//session.setAttribute();存的东西只在一次会话有效
//application.setAttribute();服务器不关机都有效
```

请求转发的实现：

```java
pageContext.forward("/s");
request.getRequestDispatcher("/s").forward(request,response);
```

### 5.el表达式(express language)

格式：${}

使用的时候如果不生效需要在页面顶端加上：

```
<%@ page isELIgnored="false"%>
```

作用

- 获取数据

获取传递的参数

- 执行运算
- 获取web开发的常用对象

### 6.转发请求时携带参数

```
<jsp:forward page="index.jsp">
    <jsp:param name="" value=""/>
    <jsp:param name="" value=""/>
    <jsp:param name="" value=""/>
</jsp:forward>
```

JSTL标签库使用步骤

引入对应的taglib

使用其中的方法

**在tomcat的jar目录也需要导入这些包，不然会包jstl解析错误**

例如：c：if的使用

```jsp
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

```

