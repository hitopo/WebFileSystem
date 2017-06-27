<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>主页</title>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <!-- <script type="text/javascript" src="js/uploadfile.js"></script> -->
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
            <div class="bar">
            <div class="upload_title"><strong>文件上传队列</strong></div>
             <div class="btns">
                 <div class="min_bar"></div>
                 <div class="close_bar"></div>
             </div>
            </div>
            <div id="fileQueue">
                <form action="servlet/UploadFileServlet" enctype="multipart/form-data" method="post">
                    <input type="file" name="uploadify" id="uploadify">
                </form>
            </div>
            <div class="action">
                <a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a>
                <!-- 上传所有文件 -->
                <a href="javascript:$('#uploadify').uploadify('cancel','*')">清空上传队列</a>
                <!-- 取消所有文件 -->
                <a href="javascript:$('#uploadify').uploadify('stop','*')">停止上传</a>
                <!-- 停止上传 -->
            </div>
            <div class="tip"></div>
        </div>
    </div>

<script type="text/javascript">
      //文件上传控件设置
      $("#uploadify").uploadify({
          'formData': {
            'userId': "${user.userId}"
          },
          //不自动上传文件
          'auto': false,
          'swf': 'uploadify/uploadify.swf',
          //检查文件是否已经存在于服务器中
          'checkExisting': 'servlet/CheckExistingServlet',
          //指定后台处理的servlet
          'uploader': 'servlet/UploadFileServlet',
          'queueID': 'fileQueue', //与下面的id对应，指定DOM元素作为文件上传的容器
          'queueSizeLimit': 5, //文件队列的最大数目
          //文件类型的说明，会显示在文件选择框中
          'fileTypeDesc': 'rar文件、zip文件或者txt文件',
          //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
          'fileTypeExts': '*.rar;*.zip;*.txt',
          'multi': true,
          'buttonText': '选择想要上传的文件',
          'buttonCursor': 'hand', //文件上传鼠标样式
          'fileSizeLimit': '1GB', //最大单个文件大小
          'width': 200, //控件宽度
          'height': 45, //控件长度
          'method': 'post',
          'onFallBack': function() {
              alert("您的浏览器不支持Flash文件上传插件");
          },
          //重写错误执行方法
          'overrideEvents': ['onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError'],
          //返回一个错误，选择文件的时候触发
          'onSelectError': function(file, errorCode, errorMsg) {
              switch (errorCode) {
                  case -100:
                      alert("上传的文件数量已经超出系统限制的" + $('#uploadify').uploadify('settings', 'queueSizeLimit') + "个文件！");
                      break;
                  case -110:
                      alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#uploadify').uploadify('settings', 'fileSizeLimit') + "大小！");
                      break;
                  case -120:
                      alert("文件 [" + file.name + "] 大小超出系统限制！");
                      break;
                  case -130:
                      alert("文件 [" + file.name + "] 类型不正确！");
                      break;
                  default:
                      alert("错误代码:" + errorCode + "\n错误信息:" + errorMsg);
                      break;
              }
              return false;
          },
          'onUploadError': function(file, errorCode, errorMsg, errorString) {
              //自己取消文件上传不弹出提示
              if (errorCode === SWFUpload.UPLOAD_ERROR.FILE_CANCELLED) {
                  return;
              }
              if (errorCode === SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
                  alert('停止文件上传');
                  return;
              }
              var msgText = "上传失败\n";
              switch (errorCode) {
                  case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
                      msgText += "HTTP 错误\n" + errorMsg;
                      break;
                  case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
                      msgText += "上传文件丢失，请重新上传";
                      break;
                  case SWFUpload.UPLOAD_ERROR.IO_ERROR:
                      msgText += "IO错误";
                      break;
                  case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
                      msgText += "安全性错误\n" + errorMsg;
                      break;
                  case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
                      msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
                      break;
                  case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
                      msgText += errorMsg;
                      break;
                  case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
                      msgText += "找不到指定文件，请重新操作";
                      break;
                  case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
                      msgText += "参数错误";
                      break;
                  default:
                      msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n" + errorMsg + "\n" + errorString;
              }
              alert(msgText);
              return false;
          },
          //当队列中的所有文件全部完成上传时触发
          'onQueueComplete': function(queueData) {
              //这里的提示如果停止文件上传会覆盖文件停止上传的提示
              //停止文件上传该函数仍然被调用
              $(".tip").html('成功上传的文件数: ' + queueData.uploadsSuccessful + '\r\n上传出错的文件数: ' + queueData.uploadsErrored);
          }
      });
</script>
</body>
</html>
