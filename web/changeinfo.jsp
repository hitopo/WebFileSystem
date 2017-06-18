<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>用户资料修改</title>
    <link rel="stylesheet" type="text/css" href="css/changeinfo.css">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/changeinfo.js"></script>
</head>

<body>
    <div class="container">
        <div class="title">个人资料</div>
        <div class="content">
            <form action="servlet/DoChangeUserInfoServlet?userid=${user.userId}" method="post">
                <div class="userName-wrapper">
                    <label for="userName">用户名：</label>
                    <input type="text" id="userName" name="userName" value="${user.userName}">
                    <span class="userNameInfo"><i class = "warn"></i></span>
                </div>
                <div class="count"></div>
                <div class="password-wrapper">
                    <label for="password">登录密码：</label>
                    <input type="password" id="password" name="password">
                    <span class="passwordInfo"><i class = "warn"></i></span>
                </div>
                <div class="strong">
                    <p class="fl">
                        <span class="active">弱</span>
                        <span class="no-active">中</span>
                        <span class="no-active">强</span>
                    </p>
                </div>
                <div class="password1-wrapper">
                    <label for="password1">确认密码：</label>
                    <input type="password" id="password1" disabled="">
                    <span class="password1Info"><i class="warn"></i></span>
                </div>
                <div class="email-wrapper">
                    <label for="email">email：</label>
                    <input type="text" id="email" name="email" value="${user.email}">
                    <span class="emailInfo"><i class="warn"></i></span>
                </div>
                <div class="btn_wrapper">
                    <input type="reset" id="resetBtn" class="btn" value="重置">
                    <input type="button" id="submitBtn" class="btn" value="确认修改">
                </div>
            </form>
        </div>
    </div>
</body>

</html>
