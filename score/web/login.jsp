<%--
  Created by IntelliJ IDEA.
  User: Hasee
  Date: 2021/7/14
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/css/mdui.min.css"
            integrity="sha384-cLRrMq39HOZdvE0j6yBojO4+1PrHfB7a9l5qLcmRm/fiWXYY+CndJPmyu5FV/9Tw"
            crossorigin="anonymous"
    />
    <meta charset="UTF-8">
</head>
<style>
    #abc{
        width: 60vw
    }
    @media only screen and (max-device-width :780px){ #abc{ width: 100vw} }
</style>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-theme-layout-auto mdui-loaded" style="margin: 10;padding: 0">
<div style=" margin: 0 auto;" id="abc">

    <div class="mdui-card">
        <div class="mdui-card-media">
            <img src="./static/image/ndm1900.jpg"/>
            <div class="mdui-card-media-covered mdui-card-media-covered-top">
                <div class="mdui-card-primary">
                    <div class="mdui-card-primary-title">请登录</div>
                </div>
            </div>
        </div>
    </div>

    <form action="/Login" method="get">
        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">学号</label>
            <input class="mdui-textfield-input" type="text" name="sno"/>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label">
            <label class="mdui-textfield-label">密码</label>
            <input class="mdui-textfield-input" type="password" name="password"/>
        </div>
        <div style="text-align: center;margin: 3rem;">
            <button type="submit" class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-theme-accent">登录</button>
        </div>
    </form>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/mdui@1.0.1/dist/js/mdui.min.js"
        integrity="sha384-gCMZcshYKOGRX9r6wbDrvF+TcCCswSHFucUzUPwka+Gr+uHgjlYvkABr95TCOz3A"
        crossorigin="anonymous"
></script>
</body>
</html>
