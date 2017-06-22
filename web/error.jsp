<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basicPath = request.getContextPath();
%>
<head>
    <title>服务器错误</title>
    <link href="<%=basicPath%>/css/error.css" rel="stylesheet"/>
</head>

<body>
<div class="container">
    <div class="row pad-top text-center">
        <div class="col-md-6 col-md-offset-3 text-center">
            <h1> 服务器找不到资源 </h1>
            <h5> 现在可以使用底部的返回按钮返回上级 </h5>
            <span id="error-link"></span>
            <h2>! ERROR DECETED !</h2>
        </div>
    </div>
    <div class="row text-center">
        <div class="col-md-8 col-md-offset-2">
            <h3><i class="fa fa-lightbulb-o fa-5x"></i></h3>
            <a href="<%=basicPath %>/main.jsp" class="btn btn-primary">返回主页</a>
        </div>
    </div>
</div>
<!-- /.container -->
<!--Core JavaScript file  -->
<script src="<%=basicPath%>/js/jquery-1.10.2.js"></script>
<!--bootstrap JavaScript file  -->
<script src="<%=basicPath%>/js/bootstrap.js"></script>
<!--Count Number JavaScript file  -->
<script src="<%=basicPath%>/js/countUp.js"></script>
<!--Custom JavaScript file  -->
<script src="<%=basicPath%>/js/custom.js"></script>
</body>

</html>
