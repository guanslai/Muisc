<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">


    <head>

        <meta charset="utf-8">
        <title>登陆网易云音乐</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="assets/js/html5.js"></script>
        <![endif]-->

    </head>
<script type="text/javascript" src="./libs/jquery-3.1.1.min.js"></script>
<script src="js/jquery-1.8.2.min.js" ></script>
<script src="js/supersized.3.2.7.min.js" ></script>
<script src="js/supersized-init.js" ></script>
<script src="js/scripts.js" ></script>
<script type="text/javascript">
$(function(){
	var $username = $("#username");
	var $password = $("#password");
	var $button = $("#login");
	$button.on('click',function(){
		if($username.val()==""||$password.val()==""){
			alert("请输入用户名密码")
		}
	})
});


</script>
<c:if test="${!empty requestScope.message}">
	<script type="text/javascript">
		var msg = "${requestScope.message}";
		alert(msg);
	</script>
</c:if>
    <body>

        <div class="page-container">
            <h1>登陆</h1>
            <form action="login.action" method="post">
                <input type="text" name="username" id="username" placeholder="请输入您的用户名！">
                <input type="password" name="password" id="password" placeholder="请输入您的用户密码！">
                <br><br><br><br>
                <button type="submit" class="submit_button" id="login">登录</button>
                <div class="error"><span>+</span></div>
            </form>
            	<br><br><br><br>
             <span class="clew_txt">还没账号？<a href="toRegister.action">立即注册</a></span>
            <div class="connect">
                <p>快捷</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
		
        

    </body>
</html>