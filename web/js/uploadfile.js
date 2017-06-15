  $(document).ready(function() {
    //文件上传控件设置
      $("#uploadify").uploadify({
          //不自动上传文件
          'auto': false,
          'swf': 'uploadify/uploadify.swf',
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
              alert("您的浏览器不支持Flash");
          },
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
              if (errorCode === SWFUpload.UPLOAD_ERROR.FILE_CANCELLED || errorCode === SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
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
              $(".tip").html('成功上传的文件数: ' + queueData.uploadsSuccessful + '\r\n上传出错的文件数: ' + queueData.uploadsErrored);
          }
      });
  });
