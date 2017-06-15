<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>用户资料修改</title>
    <style type="text/css">
        * {
            margin: 0 auto;
            padding: 0;
        }

        body {
            overflow-x : hidden;
            overflow-y : hidden;
            font-size: 16px;
            font-family: "微软雅黑", arial, serif;
        }

        .container {
            text-align: center;
            width: 80%;
            border: solid 1px #ededed;
            margin-top: 40px;
            color: #666;
        }

        .content {
            margin: 10px;
            text-align: center;
        }

        .title {
            padding: 7px 0;
            text-align: center;
            font-size: 24px;
            background: #ededed;
        }

        table {
            margin: 10px auto;
        }

        input {
            font-size: 16px;
            margin-top: 10px;
        }

        .btn_wrapper {
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .btn {
            border-radius: 2px;
            color: #fff;
            border: 1px solid #5c8ebf;
            width: 120px;
            height: 35px;
            margin-left: 30px;
            font-size: 18px;
            line-height: 18px;
            background: #6698c9;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/changeinfo.js"></script>
</head>

<body>
<div class="container">
    <div class="content">
        <div class="title">个人资料</div>
        <form action="servlet/DoChangeUserInfoServlet?userid=${user.userId}" method="post">
            <table>
                <tr>
                    <td>用户姓名：</td>
                    <td>
                        <input type="text" name="userName" value="${user.userName}">
                    </td>
                </tr>
                <tr>
                    <td>输入密码：</td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>再次输入密码：</td>
                    <td>
                        <input type="password" name="repassword">
                    </td>
                </tr>
                <tr>
                    <td>email：</td>
                    <td>
                        <input type="text" name="email" value="${user.email}">
                    </td>
                </tr>
            </table>
            <div class="btn_wrapper">
                <input type="reset" name="resetBtn" class="btn" value="重置">
                <input type="submit" name="submitBtn" class="btn" value="确认修改">
            </div>
        </form>
    </div>
</div>
</body>

</html>
