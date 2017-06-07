<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>用户登录</title>
  <link rel="stylesheet" href="css/login.css">
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/login.js"></script>
</head>
<body>
<div id="container">
  <div id="login_container">
    <div id="text">JSP网盘简单实现</div>
    <div id="loginPanel">
        <table>
          <tr id="title">
            <td colspan="2">用户登录</td>
          </tr>
          <tr>
            <td><label for="userName">用户名：</label></td>
            <td><input type="text" name = "userName" id="userName" size="25"></td>
          </tr>
          <tr>
            <td><label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label></td>
            <td><input type="password" name = "password" id="password" size = "25"></td>
          </tr>
          <tr>
            <td colspan="2" id="tip"></td>
          </tr>
          <tr class="btns">
            <td><input type="button" value="登录" class="btn" id="submitBtn"></td>
            <td><input type="button" value="退出" class="btn" id="exitBtn"></td>
          </tr>
          <tr>
            <td colspan="2"><a href="reg.jsp" title="注册">还没有用户？注册一个！</a></td>
          </tr>
        </table>
    </div>
      <div id="footer">
        ©2017 CHD
        <a href="#">移动平台</a>|
        <a href="#">服务协议</a>|
        <a href="#">权责声明</a>|
        <a href="#">帮助中心</a>|
        <a href="#">版权投诉</a>
    </div>
  </div>
</div>
</body>
</html>
