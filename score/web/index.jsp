<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@page isELIgnored="false" %>
<html lang="en">

<head>
    <title>河南大学成绩查询</title>
    <link rel="stylesheet" href="./static/css/wrap.css">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/css/mdui.min.css"
            integrity="sha384-cLRrMq39HOZdvE0j6yBojO4+1PrHfB7a9l5qLcmRm/fiWXYY+CndJPmyu5FV/9Tw"
            crossorigin="anonymous"
    />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>

<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-theme-layout-auto mdui-loaded">

<div class="scoreinquiry">
    <div class="title1">
        <!-- 标题 -->
        <span class="title">河南大学期末成绩查询</span>
    </div>
    <!-- 表单域 -->
    <div class="wrap">
        <form action="${pageContext.request.getContextPath()}/score.jsp" method="GET">
            <div class="semester">
                <!-- 下拉菜单 -->
                <div class="term">学年学期：<select name="xnxq" class="select1">
                    <option value="20200">2020年第一学期</option>
                    <option value="20201">2020年第二学期</option>
                    <option  value="20190" >2019年第一学期</option>
                    <option value="20191" >2019年第二学期</option>
                </select>
                    <!-- 学号输入框 -->
                    <div class="studentnumber ">
                        &nbsp; &nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;号：&nbsp; &nbsp;<input name="sno" type="text" style="border-radius: 30px;" value="${param.sno}">
                    </div>
                    <div class="hide_input" style="display: none">
                        <input name="token" type="text" value="${param.token}">
                        <input name="uuid" type="text" value="${param.uuid}">

                    </div>
                    <!-- 查询按钮 -->
                    <div class="btn" style="margin: 5rem"> <button type="submit" class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-theme-accent">查询</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div>
    <div class="mdui-dialog" id="dialog">
        <div class="mdui-dialog-title">好像还没有登录?</div>
        <div class="mdui-dialog-content">需要先登录获取token才能查询别人的成绩!</div>
        <div class="mdui-dialog-actions">
            <button class="mdui-btn mdui-ripple" id="btn-no123" mdui-dialog-cancel>取消</button>
            <button class="mdui-btn mdui-ripple" id="btn-ok123">确定</button>
        </div>
    </div>
</div>
<script
        src="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/js/mdui.min.js"
        integrity="sha384-gCMZcshYKOGRX9r6wbDrvF+TcCCswSHFucUzUPwka+Gr+uHgjlYvkABr95TCOz3A"
        crossorigin="anonymous"
></script>
<script>
    function getPar(par){
        var local_url = document.location.href;
        var get = local_url.indexOf(par +"=");
        if(get == -1){
            return false;
        }
        var get_par = local_url.slice(par.length + get + 1);
        var nextPar = get_par.indexOf("&");
        if(nextPar != -1){
            get_par = get_par.slice(0, nextPar);
        }
        return get_par;
    }
    var inst = new mdui.Dialog('#dialog');
    if(getPar('token')===false){
        inst.open()
    }
    $('#btn-ok123').click(function (){
        window.location="/login.jsp";
    })

</script>
</body>

</html>


