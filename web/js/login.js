$(document).ready(function() {
	// 退出按钮
	$("#exitBtn").click(function() {
		$("#loginPanel").hide();
	});
	//提示信息
	$("#userName").blur(function() {
		if ($(this).val() === "") {
			$(this).val("请输入用户名");
		}
	}).focus(function() {
		if ($(this).val() === "请输入用户名") {
			$(this).val("");
		}
	});
	//用户名和密码检验是否为空
	function checkNull() {
		if ($("#userName").val() === "" || $("#password").val() === "") {
			$("#tip").html("用户名和密码不能为空！");
			return false;
		} else {
			return true;
		}
	}

	$("#submitBtn").click(function() {
		if (!checkNull()) {
			return;
		} else {
			//执行ajax验证用户名和密码是否正确
			$.ajax({
				url: 'servlet/LoginServlet',
				type: 'POST',
				data: {
					userName: $("#userName").val(),
					password: $("#password").val()
				},
				dataType: "text",
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
				success: function(data) {
					//用户名或者密码错误
					if (data.trim() === "false") {
						alert("用户名或者密码错误");
						//将路径转回来
						return;
					} else if (data.trim() === "true") {
						//跳转到主页面
						location.href = "main.jsp";
					} else {
						alert("服务器错误");
					}
				},
				failure: function() {
					alert("服务器错误");
				}
			});
		}
	});
});