var nameLength = 0;
var xmlHttp;
/**
 * 计算给定str字符串的长度，存放在nameLength中
 */
function getNameLength(str) {
  //x00-xff代表汉字的ansi，将汉字全部换成任意两个英文字符即可计算总字符数
  return str.replace(/[^\x00-xff]/g, "xx").length;
}
/**
 * 判断是否是相同的字符
 * 是返回true，否返回false
 */
function findStr(str) {
  var firstChar = str.charAt(0);
  for (var i = 1; i < str.length; i++) {
    if (str.charAt(i) !== firstChar) {
      return false;
    }
  }
  return true;
}

window.onload = function() {
    //获取DOM对象
    var oUserName = document.getElementById('userName'),
      oUserNameInfo = document.getElementsByClassName('userNameInfo')[0],
      oPwd = document.getElementById('password'),
      oPwdInfo = document.getElementsByClassName('passwordInfo')[0],
      oPwd1 = document.getElementById('password1'),
      oPwd1Info = document.getElementsByClassName('password1Info')[0],
      oEmail = document.getElementById('email'),
      oEmailInfo = document.getElementsByClassName('emailInfo')[0],
      oCount = document.getElementsByClassName('count')[0],
      oFl = document.getElementsByClassName('fl')[0],
      btn = document.getElementById('btn'),
      loginBtn = document.getElementsByClassName('loginBtn')[0];
    //正则表达式，匹配字符串
    //\u4e00-\u9fa5表示unicode下的汉字编码范围，匹配不符合汉字和普通字符情况
    var nameReg = /[^\w\u4e00-\u9fa5]/g,
      //全是数字，匹配除了数字之外的字符
      pwdReg1 = /[^\d]/g,
      //全是字母
      pwdReg2 = /[^a-zA-z]/g;
    // 匹配eamil
    emialReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    //匹配汉字（不需要）
    // pwdReg3 = /[\u4e00-\u9fa5]/g;

    //设置用户名提示
    oUserName.onfocus = function() {
        //显示提示
        oUserNameInfo.style.display = 'inline';
        oUserNameInfo.innerHTML = '<i class = "warn"></i>请输入用户名，建议使用4-16个字符' +
          '，一个汉字两个字符，推荐使用中文用户名';
      }
      //字符数提示
    oUserName.onkeyup = function() {
        //显示字符数
        oCount.style.visibility = "visible";
        nameLength = getNameLength(this.value);
        oCount.innerHTML = nameLength + "个字符";
        //当字符数为0时，隐藏字符数提示
        if (nameLength === 0) {
          oCount.style.visibility = "hidden";
        }
      }
      //用户焦点离开用户名text时开始用户名验证
    oUserName.onblur = function() {
      //用户名为空
      if (nameLength === 0) {
        oUserNameInfo.innerHTML = '<i class = "err"></i>不能为空';
      }
      //用户名含有非法字符
      else if (nameReg.test(this.value)) {
        oUserNameInfo.innerHTML = '<i class = "err"></i>含有非法字符';
      }
      //用户名长度不符合4-16
      else if (nameLength < 4) {
        oUserNameInfo.innerHTML = '<i class = "err"></i>长度小于4个字符';
      } else if (nameLength > 16) {
        oUserNameInfo.innerHTML = '<i class = "err"></i>长度大于16个字符';
      }
      //执行ajax检查
      //若ajax检查通过，那么直接显示OK！
      else {
        doAjax(this.value);
      }
    }

    //密码提示
    oPwd.onfocus = function() {
        oPwdInfo.style.display = 'inline';
        oPwdInfo.innerHTML = '<i class = "warn"></i> ' +
          '6-16字符，请使用字母加数字或者符号，不能单独使用字母，数字或者符号';
      }
      //键盘弹起事件是用来检验是否能够开启“确认密码”
    oPwd.onkeyup = function() {
        //字符数大于5就可以
        if (this.value.length > 5) {
          //开启“确认密码”，及去除disable属性
          oPwd1.removeAttribute('disabled');
          //密码强度“中”开启
          oFl.getElementsByTagName('span')[1].className = "active";
        } else {
          oPwd1.value = "";
          oPwd1.setAttribute('disabled', '');
          oFl.getElementsByTagName('span')[1].className = "no-active";
        }
        if (this.value.length > 11) {
          //密码强度“强”
          oFl.getElementsByTagName('span')[2].className = "active";
        } else {
          oFl.getElementsByTagName('span')[2].className = "no-active";
        }
      }
      //验证密码
    oPwd.onblur = function() {
      //密码为空
      if (this.value.length === 0) {
        oPwdInfo.innerHTML = '<i class = "err"></i>不能为空';
      }
      // 相同的字符
      else if (findStr(this.value)) {
        oPwdInfo.innerHTML = '<i class = "err"></i>不能是相同的字符';
      }
      //长度不符合6-16
      else if (this.value.length < 6) {
        oPwdInfo.innerHTML = '<i class = "err"></i>长度小于6个字符';
      } else if (this.value.length > 16) {
        oPwdInfo.innerHTML = '<i class = "err"></i>长度大于16个字符';
      }
      //不符合正则表达式，全是数字或者字母，也不能存在汉字
      else if (!pwdReg1.test(this.value)) {
        oPwdInfo.innerHTML = '<i class = "err"></i>不能全是数字';
      } else if (!pwdReg2.test(this.value)) {
        oPwdInfo.innerHTML = '<i class = "err"></i>不能全是字母';
      } else {
        oPwdInfo.innerHTML = '<i class = "right"></i>OK！';
      }
    }

    //确认密码提示
    oPwd1.onfocus = function() {
      oPwd1Info.style.display = 'inline';
      oPwd1Info.innerHTML = '<i class = "warn"></i>再次输入密码';
    }

    //验证再次输入的密码
    oPwd1.onblur = function() {
        if (this.value != oPwd.value) {
          oPwd1Info.innerHTML = '<i class = "err"></i>两次输入的密码不一致';
        } else {
          oPwd1Info.innerHTML = '<i class = "right"></i>OK！';
        }
      }
      //email提示
    oEmail.onfocus = function() {
        oEmailInfo.style.display = 'inline';
        oEmailInfo.innerHTML = '<i class="warn"></i>请输入email，emial中不能含有汉字';
      }
      // 验证email
    oEmail.onblur = function() {
      if (this.value === '') {
        oEmailInfo.innerHTML = '<i class="err"></i>eamil不能为空';
      } else if (!emialReg.test(this.value)) {
        oEmailInfo.innerHTML = '<i class="err"></i>emial格式不符合要求';
      } else {
        oEmailInfo.innerHTML = '<i class="right"></i>OK！';
      }
    }

    //按钮点击特效以及提示
    btn.onclick = function() {
        //判断所有的提示是否都是OK
        if (oUserNameInfo.innerHTML.slice(-3, -1) === 'OK' &&
          oPwdInfo.innerHTML.slice(-3, -1) === 'OK' &&
          oPwd1Info.innerHTML.slice(-3, -1) === 'OK' &&
          oEmailInfo.innerHTML.slice(-3, -1) === 'OK') {
          //这里特别要注意
          //string的substring方法不支持负的参数，也就是说不能够从后往前截取
          //这是使用的是slice方法
          alert('注册成功！');
          $("form").submit();
        } else {
          alert('还有不符合要求的信息！');
        }
      }
      //放弃注册，直接登录
    loginBtn.onclick = function() {
      window.location.href = 'login.jsp';
    }
  }
  /**
   * 执行Ajax
   */
function doAjax(userName) {
  var oUserNameInfo = document.getElementsByClassName("userNameInfo")[0];
  xmlHttp = createXmlHttp();

  var url = "servlet/DoRegServlet";
  xmlHttp.onreadystatechange = function() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
      if (xmlHttp.responseText.trim() == "false") {
        oUserNameInfo.innerHTML = "<i class='err'></i>用户名已经被注册";
      } else if (xmlHttp.responseText.trim() == "true") {
        oUserNameInfo.innerHTML = "<i class='right'></i>OK！";
      }
    } 
  }
  xmlHttp.open("post", url, true);
  //处理中文乱码问题
  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
  var data = "userName="+ userName;
  xmlHttp.send(data);

}
/**
 * 创建xmlhttp对象
 * @return {[type]} [description]
 */
function createXmlHttp() {
  var xmlHttp;
  if (XMLHttpRequest) {
    xmlHttp = new XMLHttpRequest();
  } else {
    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
  }
  if (!xmlHttp) {
    alert('浏览器不支持Ajax！');
  }
  return xmlHttp;
}