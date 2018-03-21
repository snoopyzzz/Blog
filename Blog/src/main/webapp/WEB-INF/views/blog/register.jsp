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
    
</head>

<body id="login">
    <div class="login">
        <h2>博客注册信息</h2>
        <div class="login-top">
	        <c:if test="${!empty errorMsg}">
	        	<h3 style="color:red;">${errorMsg}</h3>
	        </c:if>        	
            <form action="<c:url value="doRegister" />" method="post">
                <input type="text" name="name" placeholder="用 户 名"/>
                <input type="text" name="password" id="pwd1" placeholder="密 码">
                <input type="text" name="password" id="pwd2" placeholder="密码确认" onkeyup="validate()"><span id="tishi"></span></input>
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
      </script>

</html>
