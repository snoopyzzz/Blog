<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>注册</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 设配移动端 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- Custom Theme files -->
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />" type="text/css" >
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css" />" type="text/css" media="all" />
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.min.js"/>"></script>
    
    
    
</head>

<!-- 
这里有两种验证密码是否相同的代码
第一种：密码不相同时，点击注册按钮，弹出提示框，提示两次输入的密码不相同。
第二种：密码不相同时，会在re_pwd输入框下提示，密码不相同，这时submit按钮无法点击。
 -->

<!--
<body id="login">
    <div class="login">
        <h2>博客注册信息</h2>
        <div class="login-top">
	        <c:if test="${!empty errorMsg}">
	        	<h3 style="color:red;">${errorMsg}</h3>
	        </c:if>        	
            <form action="<c:url value="doRegister" />" method="post" onsubmit="return check();">
                <input type="text" name="name" placeholder="用 户 名"/>
                <input type="text" name="password" id="pwd1" placeholder="密 码">
                <input type="text" name="pwd_confirm" id="pwd2" placeholder="密码确认">
                <input type="text" name="email" placeholder="email"/>
                <input type="text" name="phone" placeholder="phone"/>
                <input type="text" name="qq" placeholder="QQ"/>                
                <div class="forgot">
                	<input type="submit" value="注册">
            	</div>
            </form>
        </div>
        <div class="login-bottom">
            <h3><a href="login">返回登录界面</a></h3>
        </div>
    </div>
    <div class="copyright">
        <p>Copyright &copy; 2017 CUIT. All Rights Reserved.</p>
    </div>
</body>

   <script>     
      function check(){
   var password = document.getElementById("pwd1").value;
   var repsword = document.getElementById("pwd2").value;
   if(password === ''){
      alert('密码不能为空');
      return false;
   }
   if(password != repsword) {
      alert("两次密码不同，请重新输入");
      return false;
   }
    
}
      </script>

  -->
<body id="login">
    <div class="login">
        <h2>博客注册信息</h2>
        <div class="login-top">
	        <c:if test="${!empty errorMsg}">
	        	<h3 style="color:red;">${errorMsg}</h3>
	        </c:if>        	
            <form action="<c:url value="doRegister" />" method="post">
                <input type="text" name="name" id="name" placeholder="用 户 名" onblur="checkUser();"  />
                <span id="checkUN"></span>
                <input type="password" name="password" id="pwd1" placeholder="密 码">
                <input type="password" name="pwd_confirm" id="pwd2" placeholder="密码确认" onkeyup="validate()"><span id="tishi"></span>
                <input type="text" name="email" placeholder="email"/>
                <input type="text" name="phone" placeholder="phone"/>
                <input type="text" name="qq" placeholder="QQ"/>                
                <div class="forgot">  
                	<input type="submit"  id="submit" value="注册" > 
            	</div>
            </form>
        </div>
        <div class="login-bottom">
            <h3><a href="login">返回登录界面</a></h3>
        </div>
    </div>
    <div class="copyright">
        <p>Copyright &copy; 2017 CUIT. All Rights Reserved.</p>
    </div>
</body>

<script type="text/javascript">
          function validate() {
              var pwd1 = document.getElementById("pwd1").value;
              var pwd2 = document.getElementById("pwd2").value;

              if(pwd1 == pwd2) {
                  document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
                  document.getElementById("submit").disabled = false;
              }
              else {
                  document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
                document.getElementById("submit").disabled = true;
              }
          }
      
          function ine(data) {

              if (data == 1) {
                  document.getElementById("checkUN").innerHTML = "<b style='color: red'>用户名已存在</b>";
              } else if (data == 2) {
                  document.getElementById("checkUN").innerHTML = "<b style='color:red'>不能为空</b>";
              } else if (data == 3) {
                  document.getElementById("checkUN").innerHTML = "<b style='color:green'>该用户名可用</b>";
              } else if (data == 0) {
                  document.getElementById("checkUN").innerHTML = "<b style='color:red'>ajax成功返回值为空</b>";
              } else {
                  document.getElementById("checkUN").innerHTML = "<b style='color:red'>未知错误</b>";
              }
          }
          
          function checkUser() {
              var name = $('#name').val();
              var s = 0;
              $.ajax({
                  type : "post",
                  url : 'nameCheck',
                  data : "name=" + name,
                  success : function(data) {
                      ine(data);
                  }
              });

          }


          
</script>



</html>
