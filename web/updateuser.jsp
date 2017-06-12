<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>修改用户权限</title>
    <style type="text/css">
        * {
            margin: 0 auto;
            padding: 0;
        }

        body {
            font-size: 16px;
            font-family: "微软雅黑", arial;
        }

        .container {
            width: 80%;
            border: solid 1px #ededed;
            margin-top: 40px;
            color: #666;
            text-align: center;
        }

        .title {
            text-align: center;
            font-size: 24px;
            background: #ededed;
        }

        .content {
            margin-top: 10px;
            text-align: center;
        }

        select {
            width: 90px;
        }

        option {
            font-size: 16px;
        }

        .btn_wrapper {
            margin: 20px auto;
        }

        .btn {
            border-radius: 2px;
            color: #fff;
            border: 1px solid #5c8ebf;
            width: 120px;
            height: 35px;
            font-size: 18px;
            line-height: 18px;
            background: #6698c9;
            cursor: pointer;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="title">用户资料</div>
    <div class="content">
        <form action="DoUpdateUserServlet?userid=${user.userId}" method="post">
            <table>
                <tr>
                    <td>用户编号：</td>
                    <td>${user.userId}</td>
                </tr>
                <tr>
                    <td>用户姓名：</td>
                    <td>${user.userName}</td>
                </tr>
                <tr>
                    <td>用户密码：</td>
                    <td>${user.password}</td>
                </tr>
                <tr>
                    <td>email:</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>用户类型</td>
                    <td>
                        <select name="type">
                            <option value="用户">用户</option>
                            <option value="管理员">管理员</option>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="btn_wrapper">
                <input type="submit" name="submitBtn" value="确认修改" class="btn">
            </div>
        </form>
    </div>
</div>
</body>

</html>
