//用户名长度
var nameLength = 0;
//匹配不是字母、数字或者中文汉字的情况
var nameReg = /[^\w\u4e00-\u9fa5]/g,
    emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
    pwdReg1 = /[^\d]/g,
    pwdReg2 = /[^a-zA-z]/g;
/**
 *返回字符长度（中文两个字符）
 */
function getNameLength(str) {
    //将中文字符转换成两个英文字符
    return str.replace(/[^\x00-xff]/g, "xx").length;
}
/**
 * 判断是否是重复字符
 */
function finStr(str) {
    var firstChar = str.charAt(0);
    for (var i = 1; i < str.length; i++) {
        if (str.charAt(i) !== firstChar) {
            return false;
        }
    }
    return true;
}

$(document).ready(function() {

    //一上来就必须显示有几个字符
    $('.count').css('visibility', 'visible');
    nameLength = getNameLength($('#userName').val());
    $('.count').html(nameLength + '个字符');

    //用户名和邮箱必须默认显示是ok，否则如果不点击这两个输入框，ok出不来
    //也就提交不了修改
    $('.userNameInfo').css('display', 'inline');
    $('.emailInfo').css('display', 'inline');
    $('.userNameInfo').html('<i class="right"></i>OK！');
    $('.emailInfo').html('<i class="right"></i>OK！');

    //用户更改信息的验证
    //用户名提示
    $('#userName').focus(function() {
        var $info = $('.userNameInfo');
        $info.html('<i class = "warn"></i>4-16个字符，' +
            '一个汉字两个字符，推荐使用中文用户名');
    });
    //显示字符数
    $('#userName').keyup(function() {
        //显示字符提示
        $('.count').css('visibility', 'visible');
        nameLength = getNameLength($(this).val());
        $('.count').html(nameLength + '个字符');
        if (nameLength === 0) {
            $('.count').css('visibility', 'hidden');
        }
    });
    //用户名验证
    $('#userName').blur(function() {
        var $info = $('.userNameInfo');
        if ($(this).val() == '') {
            $info.html('<i class = "err"></i>不能为空');
        } else if (nameReg.test($(this).val())) {
            $info.html('<i class = "err"></i>含有非法字符');
        } else if (nameLength < 4) {
            $info.html('<i class = "err"></i>长度小于4个字符');
        } else if (nameLength > 16) {
            $info.html('<i class = "err"></i>长度大于16个字符');
        } else {
            //在这之前最好坐下ajax验证用户名是否存在
            //暂时不急着写
            $info.html('<i class="right"></i>OK！');
        }
    });
    //密码提示
    $('#password').focus(function() {
        var $info = $('.passwordInfo');
        $info.css('display', 'inline');
        $info.html('<i class = "warn"></i>' +
            '6-16个字符，不能单独使用字母，数字或者符号');
    });
    //密码测试是否能够开启确认密码
    $('#password').keyup(function() {
        //字符数大于5开启确认密码
        if ($(this).val().length > 5) {
            //打开确认密码
            $('#password1').removeAttr('disabled');
            //点亮中密码强度
            $('span').eq(3).removeClass('no-active');
            $('span').eq(3).addClass('active');
        } else {
            //关闭相关
            $('#password1').val('');
            $('#password1').attr('disabled', 'disabled');
            $('span').eq(3).removeClass('active');
            $('span').eq(3).addClass('no-active');
        }
        if ($(this).val().length > 11) {
            //密码强度强
            $('span').eq(4).removeClass('no-active');
            $('span').eq(4).addClass('active');
        } else {
            $('span').eq(4).removeClass('active');
            $('span').eq(4).addClass('no-active');
        }
    });
    //密码验证
    $('#password').blur(function() {
        $info = $('.passwordInfo');
        if ($(this).val() === '') {
            $info.html('<i class="err"></i>不能为空');
        } else if ($(this).val().length < 6) {
            $info.html('<i class="err"></i>长度小于6个字符');
        } else if ($(this).val().length > 16) {
            $info.html('<i class="err"></i>长度大于16个字符');
        } else if (finStr($(this).val())) {
            $info.html('<i class="err"></i>不能是相同字符');
        } else if (!pwdReg1.test($(this).val())) {
            $info.html('<i class = "err"></i>不能全是数字');
        } else if (!pwdReg2.test($(this).val())) {
            $info.html('<i class = "err"></i>不能全是字母');
        } else {
            $info.html('<i class="right"></i>OK！');
        }
    });
    //确认密码提示
    $('#password1').focus(function() {
        var $info = $('.password1Info');
        $info.css('display', 'inline');
        $info.html('<i class = "warn"></i>再次输入密码');
    });
    //确认密码验证
    $('#password1').blur(function() {
        var $info = $('.password1Info');
        if ($(this).val() !== $('#password').val()) {
            $info.html('<i class="err"></i>两次输入的密码不一致');
        } else {
            $info.html('<i class="right"></i>OK！');
        }
    });
    //邮箱提示
    $('#email').focus(function() {
        var $info = $('.emailInfo');
        $info.html('<i class="warn"></i>输入email，不能含有汉字');
    });
    //邮箱验证
    $('#email').blur(function() {
        var $info = $('.emailInfo');
        if ($(this).val() === '') {
            $info.html('<i class="err"></i>不能为空');
        } else if (!emailReg.test($(this).val())) {
            $info.html('<i class="err"></i>eamil格式不正确');
        } else {
            $info.html('<i class="right"></i>OK！');
        }
    });
    //确认修改按钮
    $('#submitBtn').click(function() {
        //这里特别要注意
        //string的substring方法不支持负的参数，也就是说不能够从后往前截取
        //这是使用的是slice方法
        if ($('.userNameInfo').html().slice(-3, -1) === 'OK' &&
            $('.passwordInfo').html().slice(-3, -1) === 'OK' &&
            $('.password1Info').html().slice(-3, -1) === 'OK' &&
            $('.emailInfo').html().slice(-3, -1) === 'OK') {
            //提交表单
            $("form").submit();
        } else {
            alert('还有不符合要求的信息！');
        }
    });

    //重置按钮
    $('#resetBtn').click(function() {
        $("form")[0].reset();
        //点击重置之后必须回到初始状态
        nameLength = getNameLength($('#userName').val());
        $('.count').html(nameLength + '个字符');

        //重置提示信息
        $('.userNameInfo').html('<i class="right"></i>OK！');
        $('.emailInfo').html('<i class="right"></i>OK！');

        //关闭密码强度
        $('span').eq(3).removeClass('active');
        $('span').eq(3).addClass('no-active');
        $('span').eq(4).removeClass('active');
        $('span').eq(4).addClass('no-active');

        //关闭重新输入密码框
        $('#password1').attr('disabled', 'disabled');
    });
});
