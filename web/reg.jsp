<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="css/reg.css">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src = "js/reg.js"></script>
</head>
<body>
<div class="container">
    <div id = "title">
        <h1>网盘注册</h1>
        <div class="login">
            我已注册，现在就<button class="loginBtn">登录</button>
        </div>
        <hr>
    </div>
    <form action="servlet/RegServlet" method="post">
        <div class  = "userName-wrapper">
            <label for="userName">用户名：</label>
            <input type="text" id = "userName" name="userName">
            <span class = "userNameInfo"><i class = "warn"></i></span>
        </div>
        <div class = "count"></div>
        <div class = "password-wrapper">
            <label for="password">登录密码：</label>
            <input type="password" id = "password" name="password">
            <span class = "passwordInfo"><i class = "warn"></i></span>
        </div>
        <div class = "strong">
            <p class = "fl">
                <span class = "active">弱</span>
                <span class = "no-active">中</span>
                <span class = "no-active">强</span>
            </p>
        </div>
        <div class = "password1-wrapper">
            <label for="password1">确认密码：</label>
            <input type="password" id ="password1" disabled="">
            <span class = "password1Info"><i class="warn"></i></span>
        </div>
        <div class = "email-wrapper">
            <label for="email">email：</label>
            <input type="text" id ="email" name="email">
            <span class = "emailInfo"><i class="warn"></i></span>
        </div>
        <div class = "btn-wrapper">
            <input type="button" id  = "btn" class= "btn" value = "同意协议并注册">
        </div>
    </form>
</div>
</body>
</html>
