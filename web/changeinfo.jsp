<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="" %>
<html>
<head>
    <title>用户资料修改</title>
</head>
<body>
<form action="DoChangeUserInfoServlet?userid=${user.userId}" method="post">
    <table>
        <tr>
            <td>用户姓名：</td>
            <td><input type="text" name="userName" value="${user.userName}"></td>
        </tr>
        <tr>
            <td>输入密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>再次输入密码：</td>
            <td><input type="password" name="repassword"></td>
        </tr>
        <tr>
            <td>email：</td>
            <td><input type="text" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="submitBtn" value="确认修改用户信息">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="reset" name="resetBtn" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
