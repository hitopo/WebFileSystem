<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>显示文件</title>
    <style type="text/css">
        h1 {
            text-align: center;
        }

        table {
            width: 800px;
            font-size: 20px;
            font-family: "微软雅黑", serif;
            text-align: center;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>文件信息</h1>
<table border="1" cellspacing="0" cellpadding="0">
    <tr>
        <th>文件名</th>
        <th>文件大小</th>
        <th>上传时间</th>
        <th>下载</th>
        <th>删除</th>
    </tr>
    <c:forEach var="file" items="${fileList}" >
        <tr>
            <td>${file.fileName}</td>
            <td>${file.fileSize}</td>
            <td>${file.time}</td>
            <td><a href="DownloadFileServlet?fileid=${file.fileId}">下载</a></td>
            <td><a href="DeleteFileServlet?fileid=${file.fileId}">删除</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
