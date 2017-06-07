<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>修改用户权限</title>
</head>

<body>
    <form action="DoUpdateUserServlet?userid=${user.userId}" method="post">
        <table>
            <tr>
                <td>用户编号：</td>
                <td>${user.userId}</td>
            </tr>
            <tr>
                <td>用户密码：</td>
                <td>${user.userName}</td>
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
            <tr>
                <td colspan="2">
                    <input type="submit" name="submitBtn" value="确认修改用户类型">
                </td>
            </tr>
        </table>
    </form>
</body>

</html>
