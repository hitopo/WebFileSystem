$(document).ready(function() {
    //关于我们
    $("#about_us").click(function() {
        $("#content_info").attr('src', 'aboutus.html');
        //改变背景色
        // $(this).css('backgroundColor', '#4AC8EA');
        // $("#info_change").css('backgroundColor', '#EFF4F8');
        // $("#file_upload").css('backgroundColor', '#EFF4F8');
        // $("#file_download").css('backgroundColor', '#EFF4F8');
        // $("#user_manage").css('backgroundColor', '#EFF4F8');
    });
    //修改资料
    $("#info_change").click(function() {
        $("#content_info").attr('src', 'servlet/ChangeUserInfoServlet');
        //改变背景颜色
        // $(this).css('backgroundColor', '#4AC8EA');
        // $("#about_us").css('backgroundColor', '#EFF4F8');
        // $("#file_upload").css('backgroundColor', '#EFF4F8');
        // $("#file_download").css('backgroundColor', '#EFF4F8');
        // $("#user_manage").css('backgroundColor', '#EFF4F8');
    });
    //文件上传
    $("#file_upload").click(function() {
        $("#content_info").attr('src', 'uploadfile.jsp');
        //改变背景色
        // $(this).css('backgroundColor', '#4AC8EA');
        // $("#info_change").css('backgroundColor', '#EFF4F8');
        // $("#about_us").css('backgroundColor', '#EFF4F8');
        // $("#file_download").css('backgroundColor', '#EFF4F8');
        // $("#user_manage").css('backgroundColor', '#EFF4F8');
    });
    //文件下载
    $("#file_download").click(function() {
        $("#content_info").attr('src', 'servlet/ListFileServlet');
        //改变背景色
        // $(this).css('backgroundColor', '#4AC8EA');
        // $("#info_change").css('backgroundColor', '#EFF4F8');
        // $("#file_upload").css('backgroundColor', '#EFF4F8');
        // $("#about_us").css('backgroundColor', '#EFF4F8');
        // $("#user_manage").css('backgroundColor', '#EFF4F8');
    });
    //用户管理界面
    $("#user_manage").click(function() {
        $("#content_info").attr('src', 'servlet/ListUserServlet');
        //改变背景色
        // $(this).css('backgroundColor', '#4AC8EA');
        // $("#info_change").css('backgroundColor', '#EFF4F8');
        // $("#file_upload").css('backgroundColor', '#EFF4F8');
        // $("#about_us").css('backgroundColor', '#EFF4F8');
        // $("#file_download").css('backgroundColor', '#EFF4F8');
    });
    //注销按钮，直接返回登录界面
    $("#log_off").click(function() {
        location.href = "login.jsp";
    });
    // 实现列表的悬停效果
    $("li").hover(function() {
        $(this).css('backgroundColor', '#4AC8EA');
    }, function() {
        $(this).css('backgroundColor', '#EFF4F8');
    });
});
