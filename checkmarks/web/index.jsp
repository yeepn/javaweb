<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@page isELIgnored="false" %>
<html lang="en">

<head>
    <title>河南大学成绩查询</title>
    <link rel="stylesheet" href="./static/css/wrap.css">
</head>

<body>
<div class="scoreinquiry">
    <div class="title1">
        <!-- 标题 -->
        <span class="title">河南大学期末成绩查询</span>
    </div>
    <!-- 表单域 -->
    <div class="wrap">
        <form action="${pageContext.request.getContextPath()}/checkmarks" method="GET">
            <div class="semester">
                <!-- 下拉菜单 -->
                <div class="term">学年学期：<select name="xnxq" class="select1">
                    <option value="20200">2020年第一学期</option>
                    <option  value="20190" >2019年第一学期</option>
                    <option value="20191" >2019年第二学期</option>
                </select>
                    <!-- 学号输入框 -->
                    <div class="studentnumber ">
                        &nbsp; &nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;号：&nbsp; &nbsp;<input name="sno" type="text" style="border-radius: 30px;">
                    </div>
                    <!-- 查询按钮 -->
                    <div class="btn"><button>查询</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>

</html>


