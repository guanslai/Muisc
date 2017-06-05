<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="css/unLogin.css">
</head>
<script type="text/javascript" src="./libs/jquery-3.1.1.min.js"></script>
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
<!-- 图片轮播 -->
<div class="carouselFigure">
	<div></div>
</div>
<!-- 内容体 -->
<div class="musicBody">
	<div class="musicBox">
		<div class="leftBox">
			<!-- 热门音乐 -->
			<div class="hotMusicTop">
				<div class="hotLogo"></div>
				<div class="hotNav">
					<ul>
						<li><a href="secByLanguage.action?area=华语">华语</a></li>
						<li><a href="secByType.action?type=流行">流行</a></li>
						<li><a href="secByType.action?type=摇滚">摇滚</a></li>
						<li><a href="secByType.action?type=民谣">民谣</a></li>
						<li><a href="secByType.action?type=电子">电子</a></li>
					</ul>
				</div>
				<div class="hotMore">
					<a href="toMusicList.action">更多</a>
				</div>
			</div>
			<div class="redLine"></div>
			<div class="hotMusic">
				<div class="hotMusic_1">
					<c:forEach items="${hitSingles}" var="hot" varStatus="status">
						<c:choose>
							<c:when test="${status.count%4==0}">
								<div class="hotMusic_1_4">
									<img src="./images/hotMusic_${status.count}.png"><br>
									<a href="#">${hot.musicName}</a>
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<img src="./images/hotMusic_${status.count}.png"><br>
									<a href="#">${hot.musicName}</a>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<!-- 新碟上架 -->
			<div class="newAlbumTop">
				<div class="newAlbumLogo"></div>
				<div class="newAlbumMore">
					<a href="toUnfinish.action">更多</a>
				</div>
			</div>
			<div class="redLine"></div>
			<div class="newAlbum">
				<div class="newAlbum_1">
					<c:forEach items="${newAlbum}" var="news" varStatus="status">
						<c:choose>
							<c:when test="${status.count%5==0}">
								<div><img src="./images/newAlbum_${status.count}.png"><br><a href="">${news.musicName}</a></div>
							</c:when>
							<c:otherwise>
								<div class="newAlbum_1_5"><img src="./images/newAlbum_${status.count}.png"><br><a href="#">${news.musicName}</a></div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<!-- 榜单 -->
			<div class="billboardTop">
				<div class="billboardLogo"></div>
				<div class="billboardMore">
					<a href="toRankList.action">更多</a>
				</div>
			</div>
			<div class="redLine"></div>
			<div class="billboard">
				<div class="billboard_1">
					<div class="billboard_1_1">
						<div class="billboardTop">
							<img src="./images/billboard_1.png">
						</div>
						<div class="billboardBody">
							<!-- 飙升榜 -->
							<table>
								<c:forEach items="${soaringMusic}" var="soaring" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td><a href="javascript:void(0)">${soaring.musicName}</a></td>
									</tr>
								</c:forEach>
							</table>
							<div>
								<a href="toRankList.action">查看全部></a>
							</div>
						</div>
					</div>
					<div class="billboard_1_1">
						<div class="billboardTop">
							<img src="./images/billboard_2.png">
						</div>
						<div class="billboardBody">
							<!-- 新歌榜 -->
							<table>
								<c:forEach items="${newMusic}" var="news" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td><a href="javascript:void(0)">${news.musicName}</a></td>
									</tr>
								</c:forEach>
							</table>
							<div>
								<a href="toRankList.action">查看全部></a>
							</div>
						</div>
					</div>
					<div class="billboard_1_1 billboard_1_3">
						<div class="billboardTop">
							<img src="./images/billboard_3.png">
						</div>
						<div class="billboardBody">
							<!-- 原创歌曲榜 -->
							<table>
								<c:forEach items="${originalMusic}" var="original" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td><a href="javascript:void(0)">${original.musicName}</a></td>
									</tr>
								</c:forEach>
							</table>
							<div>
								<a href="toRankList.action">查看全部></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="rightBox">
			<div class="rightBox_1">
				<div class="rightBox_1_1">
					<div class="rightBox_1_1_left"></div>
					<div class="rightBox_1_1_right">
						<div class="rig_1">
							用户未登录
						</div>
						<div class="rig_2"></div>
					</div>
				</div>
				<div class="rightBox_1_2">
					
				</div>
			</div>
			<div class="rightBox_2"></div>
			<div class="rightBox_3"></div>
		</div>
	</div>
</div>
<!-- 底部 -->
<div class="bottom"></div>
</body>
<c:if test="${!empty requestScope.message}">
	<script type="text/javascript">
		var msg = "${requestScope.message}";
		alert(msg);
	</script>
</c:if>
</html>