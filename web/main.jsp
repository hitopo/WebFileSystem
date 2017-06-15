<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>主页</title>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/uploadfile.js"></script>
    <script type="text/javascript" src="uploadify/jquery.uploadify.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="uploadify/uploadify.css" rel="stylesheet" type="text/css">
</head>

<body>
    <div id="container">
        <div id="header">
            <div id="title">
                <div id="icon"><img src="images/title.png"></div>
                <div id="text">JSP网盘</div>
            </div>
            <div id="info">
                <div id="user"><img src="images/user.png">${user.userName}</div>
                <div id="btn">
                    <button id="log_off">注销</button>
                </div>
            </div>
        </div>
        <div id="navi">
            <ul>
                <li id="info_change" class="firstLi">
                    <img src="images/infochange.png"> 资料修改
                </li>
                <li id="file_upload" class="normalLi">
                    <img src="images/upload.png"> 文件上传
                </li>
                <li id="file_download" class="normalLi">
                    <img src="images/download.png"> 文件下载
                </li>
                <%
                User user = (User) session.getAttribute("user");
                //获取用户类型
                //在js文件中使用userType实现权限控制
                if ("管理员".equals(user.getType())) {
            %>
                    <li id="user_manage" class="normalLi">
                        <img src="images/usermanage.png"> 用户管理
                    </li>
                    <%
                }
            %>
                        <li id="about_us" class="normalLi">关于我们</li>
            </ul>
            </ul>
        </div>
        <div id="content">
            <iframe id="content_info" src="servlet/ListFileServlet"></iframe>
        </div>
        <div id="footer">
            ©2017 CHD
            <a href="#">移动平台</a>|
            <a href="#">服务协议</a>|
            <a href="#">权责声明</a>|
            <a href="#">帮助中心</a>|
            <a href="#">版权投诉</a>
        </div>
        <div id="upload_list">
            <div id="bar">
             <div id="btns"></div>
            </div>
            <div id="fileQueue">
                <form action="servlet/FileUploadServlet" enctype="multipart/form-data" method="post">
                    <input type="file" name="uploadify" id="uploadify">
                </form>
            </div>
            <div id="action">
                <a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>
                <!-- 上传所有文件 -->
                <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a>
                <!-- 取消所有文件 -->
                <a href="javascript:$('#uploadify').uploadify('stop','*')">停止上传</a>
                <!-- 停止上传 -->
            </div>
            <div id="tip"></div>
        </div>
    </div>
</body>
</html>
