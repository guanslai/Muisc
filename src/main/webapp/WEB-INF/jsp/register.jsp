<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="js/supersized-init.js" ></script>

</head>
<c:if test="${!empty requestScope.message}">
	<script type="text/javascript">
		var msg = "${requestScope.message}";
		alert(msg);
	</script>
</c:if>
<body class="login_bj" >

<div class="zhuce_body">
	<div class="logo"></div>
    <div class="zhuce_kong">
    	<div class="zc">
        	<div class="bj_bai">
            <h3>欢迎注册</h3>
       	  	  <form action="register.action" method="post">
	                <input name="username" type="text" class="kuang_txt phone" placeholder="用户名">
	                <input name="email" type="text" class="kuang_txt email" placeholder="邮箱">
	                <input name="password" type="password" class="kuang_txt possword" placeholder="密码">
	                <input name="pw" type="password" class="kuang_txt possword" placeholder="确认密码">
	                <div>
	               		<input name="" type="checkbox" value=""><span>已阅读并同意<a href="#" target="_blank"><span class="lan">《哆来咪音乐使用协议》</span></a></span>
	                </div>
	                <input name="注册" type="submit" class="btn_zhuce" value="注册">
                </form>
            </div>
        	<div class="bj_right">
            	<p>使用以下账号直接登录</p>
                <a href="#" class="zhuce_qq">QQ注册</a>
                <a href="#" class="zhuce_wb">微博注册</a>
                <a href="#" class="zhuce_wx">微信注册</a>
                <p>已有账号？<a href="toLogin.action">立即登录</a></p>
            
            </div>
        </div>
    </div>

</div>
    
<div style="text-align:center;">
</div>

</body>
</html>