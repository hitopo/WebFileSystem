<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>用户展示</title>
    <style type="text/css">
    	h1{
    		text-align: center;
    	}
    	table{
    		width:800px;
    		font-size:20px;
            font-family: "微软雅黑", serif;
				text-align: center;
			}
		td{
			text-align:center;
		}
    </style>
</head>
<body>
   <h1>用户信息</h1>
   <table  border="1" cellspacing="0" cellpadding="0">
       <tr>
           <th>用户编号</th>
           <th>用户姓名</th>
           <th>用户密码</th>
           <th>email</th>
           <th>用户类型</th>
           <th>修改</th>
           <th>删除</th>
       </tr>
       <!-- 循环调用显示所有学生信息 -->
       <c:forEach var="user" items="${userList}">
           <tr>
               <td>${ user.userId}</td>
               <td>${ user.userName}</td>
               <td>${ user.password}</td>
               <td>${ user.email}</td>
               <td>${ user.type}</td>
               <td><a href="UpdateUserServlet?userid=${user.userId}">修改权限</a></td>
               <td><a href = "DeleteUserServlet?userid=${user.userId}" onclick="return confirm('确定要删除吗？');">删除</a></td>
           </tr>
       </c:forEach>
   </table>
</body>
</html>
