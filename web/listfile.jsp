<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>显示文件</title>
    <style type="text/css">
        * {
            margin:0 10px;
            padding:0;
        }
        body{
            overflow-x : hidden;
            overflow-y : hidden;
        }
        table{
            margin-top:5px;
            width:100%;
            font-size:20px;
            font-family: "微软雅黑", serif;
            text-align: center;
            
        }
        td,th{
            text-align:center;
            padding: 5px;
            color: rgb(95, 74, 121);
            cursor: default;
        }
         tr:nth-child(even) {
            background: rgb(223, 216, 232);
        }
        tr:nth-child(odd) {
           background: #FFF;
        }
        form {
            display:inline;
        }
        .footer {
            margin-top:15px;
            text-align: center;
        }
        .submitBtn {
            width:45px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<table>
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
            <td><a href="DeleteFileServlet?fileid=${file.fileId}" onclick="return confirm('确定要删除吗？');">删除</a></td>
        </tr>
    </c:forEach>
</table>
    <%
        String pageTurningUrl = "ListFileServlet?num=";
    %>
    <div class="footer">
        <c:choose>
            <c:when test="${page.num!=1}">
                <a href="<%=pageTurningUrl%>${page.first}">首页</a>
                <a href="<%=pageTurningUrl%>${page.prev}">上一页</a>
            </c:when>
            <c:otherwise>
                <b>首页</b>
                <b>上一页</b>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${page.num!=page.pageCount}">
                <a href="<%=pageTurningUrl%>${page.next}">下一页</a>
                <a href="<%=pageTurningUrl%>${page.last}">尾页</a>
            </c:when>
            <c:otherwise>
                <b>下一页</b>
                <b>尾页</b>
            </c:otherwise>
        </c:choose>

        <form action="ListFileServlet" method="GET">
            <input type="text"  size="2" name="num">
            <input type="submit"  value="跳转" class="submitBtn">
        </form>
        <c:out value="当前是第${page.num}页"></c:out>
        <c:out value="总共${page.pageCount}页"></c:out>
    </div>

</body>
</html>
