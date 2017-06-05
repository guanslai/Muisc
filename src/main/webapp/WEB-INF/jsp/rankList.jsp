<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>排行榜</title>
<link rel="stylesheet" href="frameworks/bootstrap-3.3.0-dist/css/bootstrap.min.css">
<!-- 自定义 -->
<link rel="stylesheet" type="text/css" href="css/rankList.css">
</head>
<script type="text/javascript" src="./libs/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.bodyLeft_2').on('click',function(){
			var $input = $(this).find('input');
			var href = "changeRank.action?num="+$input.val();
			var $a = $('.changeLink a');
			$a.attr('href',href);
			$a[0].click();
		})
	});
</script>

<body>
	<div class="top">
		<div class="logo">
			<div></div>
		</div>
		<div class="nav">
			<ul>
				<li><a href="toActivity.action">发现音乐</a></li>
				<li><a href="toMyMusic.action">我的音乐</a></li>
				<li><a href="#">朋友</a></li>
				<li><a href="#">商城</a></li>
				<li><a href="#">音乐人</a></li>
				<li><a href="#">下载客户端</a></li>
				
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
				<li><a href="#">主播电台</a></li>
				<li><a href="#">歌手</a></li>
				<li><a href="#">新碟上架</a></li>
			</ul>
		</div>
	</div>
	<!-- 内容  -->
	<div class="listBody">
		<div class="listBox">
			<div class="bodyLeft">
				<div class="bodyLeft_1">云音乐特色榜</div>
				<div class="bodyLeft_2">
					<div class="leftLogo">
						<div>
							<img src="./images/litBS.png">
						</div>
					</div>
					<div class="leftText">
						<div class="leftText_1">
							<input type='hidden' value='1'/>
							云音乐飙升榜
						</div>
						<div class="leftText_2">
							每周更新
						</div>
					</div>
				</div>
				<div class="bodyLeft_2">
					<div class="leftLogo">
						<div>
							<img src="./images/litXG.png">
						</div>
					</div>
					<div class="leftText">
							<div class="leftText_1">
								<input type='hidden' value='2'/>
								云音乐新歌榜
							</div>
							<div class="leftText_2">
								每周更新
							</div>
					</div>
				</div>
				<div class="bodyLeft_2">
					<div class="leftLogo">
						<div>	
							<img src="./images/litYC.png">
						</div>
					</div>
					<div class="leftText">
							<div class="leftText_1">
								<input type='hidden' value='3'/>
								云音乐原创歌曲榜
							</div>
							<div class="leftText_2">
								每周更新
							</div>
					</div>
				</div>
				<div class="bodyLeft_2">
					<div class="leftLogo">
						<div>
							<img src="./images/litRG.png">
						</div>
					</div>
					<div class="leftText">
							<div class="leftText_1">
								<input type='hidden' value='4'/>
								云音乐热歌榜
							</div>
							<div class="leftText_2">
								每周更新
							</div>
					</div>
				</div>
				<!-- 隐藏的，使用a标签进行跳转 -->
				<div class="changeLink">
					<a href="javascript:void(0)"></a>
				</div>
				<!-- 功能未实现 -->
				<div class="bodyLeft_3">
					<div>
						<img src="./images/leftBox.png">
					</div>	
				</div>
			</div> <!-- 内容左侧结束 -->
			
			<!-- 内容右侧 -->
			<div class="bodyRight">
				<div class="bodyRightTop">
					<div class="topLogo">
						<div>
							<img src="./images/ListLogo.png">
						</div>
					</div>
					<div class="topOperation">
						<div class="topOperation_1">
							<span>${rankName}</span>
						</div>
						<div class="topOperation_2">
							<div class="playButton">
								<input type="image" src="./images/playMusicButton.png">
							</div>
							<div class="other">
								<img src="./images/navButton.png">
							</div>
						</div>
					</div>
				</div>
				<div class="bodyRightNav">
					<div>歌曲列表</div>
				</div>
				<div class="redLine"></div>
				<div class="muiscList">
					<table class="listTable">
						<thead class="listThead">
							<tr height="40px">
								<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th>操作</th>
									<th>音乐标题</th>
									<th>歌手</th>
									<th>专辑</th>
									<th>时长</th>
							</tr>
						</thead>
						<tbody class="listTbody">
							<c:forEach items="${rankList}" var="list" varStatus="status">
								<tr height="40px">
										<td>${status.count}</td>
										<td>播放</td>
										<td>${list.musicName}</td>
										<td>${list.album.singer}</td>
										<td>${list.album.albumName}</td>
										<td>5:00<td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div><!-- 内容右侧结束 -->
		</div>
	</div>
	<!-- 底部 -->
	<div class="bottom"></div>
</body>
</html>