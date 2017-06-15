<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
   <style type="text/css">
       
   </style>
   <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
   <script type="text/javascript">
        $(document).ready(function() {
            $(".btn").click(function() {
                //显示main.jsp（父级窗口）的上传列表
                $("#upload_list",parent.document).show();
            });
        });
   </script>
</head>
<body>
<h1>文件上传</h1>
<div class="info">
	<ul>
		<strong>上传提示</strong>
		<li>一次最多上传5个文件</li>
		<li>单个文件大小必须控制在1GB以内</li>
		<li>为了网站的安全，只允许上传.rar .zip .txt文件</li>
		<li>上传文件的过程中，不能离开上传页面，否则会停止上传</li>
	</ul>
</div>
<div class="uploadBtn"><button class="btn">文件上传</button></div>
</body>
</html>