<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>歌单</title>
<link rel="stylesheet" href="frameworks/bootstrap-3.3.0-dist/css/bootstrap.min.css">
<!-- 自定义 -->
<link rel="stylesheet" type="text/css" href="css/activity.css">
<link rel="stylesheet" type="text/css" href="css/musicList.css">
<script type="text/javascript" src="./libs/jquery-3.1.1.min.js"></script>
</head>
<script type="text/javascript">
	//控制div的隐藏和显示 
	$(function(){
		$('.selectButton input').on('click',function(){
			if($('.selectDiv').is(':hidden')){
				$('.selectDiv').show(250);
			}else{
				$('.selectDiv').hide(500);
			}
		})		
	});
</script>
<body>
	<!-- 头部 -->
<div class="top">
	<div class="logo">
		<div></div>
	</div>
	<div class="nav">
		<ul>
			<li><a href="toActivity.action">发现音乐</a></li>
			<li><a href="toMyMusic.action">我的音乐</a></li>
			<li><a href="toUnfinish.action">朋友</a></li>
			<li><a href="toUnfinish.action">商城</a></li>
			<li><a href="toUnfinish.action">音乐人</a></li>
			<li><a href="toUnfinish.action">下载客户端</a></li>
			
		</ul>
	</div>
	<div class="search">
		<div class="searchInput">
			<input type="text" name="search">
		</div>
		<div class="login">
			<a href="toLogin.action">登陆</a>
		</div>
	</div>
</div>
<!-- 二级头部 -->
<div class="secondTop">
	<div>
		<ul>
			<li><a href="toActivity.action">推荐</a></li>
			<li><a href="toRankList.action">排行榜</a></li>
			<li><a href="toMusicList.action">歌单</a></li>
			<li><a href="toUnfinish.action">主播电台</a></li>
			<li><a href="toUnfinish.action">歌手</a></li>
			<li><a href="toUnfinish.action">新碟上架</a></li>
		</ul>
	</div>
</div>
<div class="MusicList">
	<div class="MusicListBody">
		<div class="MusicListBodyTop">
			<div class="selectButton">
				<div>
					<span>${condition}</span>
				</div>
				<div>
					<input type="image" src="./images/selectButton.png">
				</div>
			</div>
		</div>
		<div class="redLine"></div>
		<div class="musicListBodyCenter">
			<!-- 分类查询的弹窗div -->
			<div class="selectDiv">
				<div class="selectDivNav">
					<div>
						<a href="toMusicList.action"><img src="./images/selectAllButton.png"></a>
					</div>
				</div>
				<div class="divBody">
					<div class="divBodyLeft">
						<div>
							<img src="./images/language.png">
						</div>
						<div>
							<img src="./images/type.png">
						</div>
						<div>
							<img src="./images/style.png">
						</div>
					</div>
					<div class="divBodyRight">
						<div class="languageDiv">
							<a href="secByLanguage.action?area=华语">华语</a>
							<a href="secByLanguage.action?area=欧美">欧美</a>
							<a href="secByLanguage.action?area=日韩">日韩</a>
							<a href="secByLanguage.action?area=粤语">粤语</a>
						</div>
						<div class="typeDiv">
							<a href="secByType.action?type=流行">流行</a>
							<a href="secByType.action?type=摇滚">摇滚</a>
							<a href="secByType.action?type=爵士">爵士</a>
							<a href="secByType.action?type=民谣">民谣</a>
							<a href="secByType.action?type=电子">电子</a>
						</div>
						<div class="styleDiv">
							<a href="secByStyle.action?style=忧伤">忧伤</a>
							<a href="secByStyle.action?style=怀旧">怀旧</a>
							<a href="secByStyle.action?style=爱情">爱情</a>
							<a href="secByStyle.action?style=激情">激情</a>
							<a href="secByStyle.action?style=浪漫">浪漫</a>
						</div>
					</div>
				</div>
			</div>
			<c:forEach items="${listMusic}" var="list" varStatus="status">
				<c:choose>
					<c:when test="${status.count%5==0}">
						<div class="five divOne">
							<img src="./images/list${status.count}.png"><br>
							<a href="#">${list.musicName}</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="divOne">
							<img src="./images/list${status.count}.png"><br>
							<a href="#">${list.musicName}</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</div>
<!-- 底部 -->
<div class="bottom"></div>
</body>
</html>