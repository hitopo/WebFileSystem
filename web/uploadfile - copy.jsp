<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();

%>
<html>
<head>
    <title>文件上传</title>
    <link href="uploadify/uploadify.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="uploadify/jquery.uploadify.min.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#uploadify").uploadify({
                'auto': false,
                'swf': '<%=path%>/uploadify/uploadify.swf',
                'uploader': '<%=path%>/servlet/UploadFileServlet', //指定后台处理的servlet
                'queueID': 'fileQueue', //与下面的id对应，指定DOM元素作为文件上传的容器
                'queueSizeLimit': 5, //文件队列的最大数目
                'fileTypeDesc': 'rar文件、zip文件或者txt文件', //文件类型的说明，会显示在文件选择框中
                'fileTypeExts': '*.rar;*.zip;*.txt', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
                'multi': true,
                'buttonText': '选择想要上传的文件',
                'buttonCursor': 'hand',//文件上传鼠标样式
                'fileSizeLimit': '1GB',//最大单个文件大小
                'width': 200,//控件宽度
                'height': 50,//控件长度
                'method':'post',
                'onFallBack': function () {
                    alert("您的浏览器不支持Flash");
                },
                //这里可以传递参数到后台，后台进行逻辑处理，非常重要！
                //可以从后台直接获取FileItem对象，无需前台传递
                /* 'onUploadStart':function(file) {
                 $('#uploadify').uploadify("settings","formData",{'name':file.name,'size':file.size});
                 }, */
//                'onUploadSuccess': function (file, data, response) {
//                    //上传完成时触发（每个文件触发一次）
//                    if (data.trim() == "1" && response == true) {
//                        $("#tip").html("");
//                    }
//                },
                onUploadError: function (file, errorCode, errorMsg, errorString) {
                    //上传文件出错是触发（每个出错文件触发一次）
                    $("#tip").html("文件上传失败");
                    alert('错误代码: ' + errorCode + '\r\n'
                        + ' - 错误描述: ' + errorMsg + '\r\n'
                        + ' - 简要错误描述: ' + errorString);
                },
                onQueueComplete: function (queueData) {
                    //当队列中的所有文件全部完成上传时触发
                    $("#tip").html('成功上传的文件数: ' + queueData.uploadsSuccessful
                        + '\r\n上传出错的文件数: ' + queueData.uploadsErrored);
                }
            });
        });
    </script>
</head>
<body>
<div id="fileQueue"></div>
<form action="servlet/FileUploadServlet" enctype="multipart/form-data" method="post">
    <input type="file" name="uploadify" id="uploadify"/>
    <div id="action">
        <a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>
        <!-- 上传所有文件 -->
        <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a>
        <!-- 取消所有文件 -->
        <a href="javascript:$('#uploadify').uploadify('stop','*')">停止上传</a>
        <!-- 停止上传 -->
    </div>
    <div id="tip"></div>
</form>
</body>
</html>