$(document).ready(function() {
    //关于我们
    $("#about_us").click(function() {
        $("#content_info").attr('src', 'aboutus.html');
    });
    //修改资料
    $("#info_change").click(function() {
        $("#content_info").attr('src', 'servlet/ChangeUserInfoServlet');
    });
    //文件上传
    $("#file_upload").click(function() {
        $("#content_info").attr('src', 'uploadfile.jsp');
    });
    //文件下载
    $("#file_download").click(function() {
        $("#content_info").attr('src', 'servlet/ListFileServlet');
    });
    //用户管理界面
    $("#user_manage").click(function() {
        $("#content_info").attr('src', 'servlet/ListUserServlet');
    });
    //注销按钮，直接返回登录界面
    $("#log_off").click(function() {
        location.href = "login.jsp";
    });
});
