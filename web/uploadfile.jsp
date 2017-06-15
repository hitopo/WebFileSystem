<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style type="text/css">
        * {
            margin: 0 auto;
            padding: 0;
        }

        body {
            overflow-x: hidden;
            overflow-y: hidden;
            font-size: 16px;
            font-family: "微软雅黑", arial, serif;
        }

        .container {
            width: 80%;
            border: solid 1px #ededed;
            margin-top: 40px;
            color: #666;
        }

        .content {
            margin: 10px;
        }

        strong {
            font-size: 18px;
        }

        ul {
            margin: 10px;
        }

        li {
            margin-top: 10px;
            list-style: none;
        }

        .title {
            padding: 7px 0;
            text-align: center;
            font-size: 24px;
            background: #ededed;
        }

        .btn_wrapper {
            text-align: center;
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .btn {
            border-radius: 2px;
            color: #fff;
            border: 1px solid #5c8ebf;
            width: 120px;
            height: 35px;
            margin-left: 30px;
            font-size: 18px;
            line-height: 18px;
            background: #6698c9;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".btn").click(function () {
                //显示main.jsp（父级窗口）的上传列表
                $("#upload_list", parent.document).show();
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="title">文件上传</div>
    <div class="info">
        <ul>
            <strong>上传提示：</strong>
            <li>1.一次最多上传5个文件</li>
            <li>2.单个文件大小必须控制在1GB以内</li>
            <li>3.为了网站的安全，只允许上传.rar .zip .txt文件</li>
            <li>4.上传文件的过程中，不能离开上传页面，否则会停止上传</li>
        </ul>
    </div>
    <div class="btn_wrapper">
        <button class="btn">文件上传</button>
    </div>
</div>
</body>
</html>