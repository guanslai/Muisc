<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的歌单</title>
<link rel="stylesheet" href="frameworks/bootstrap-3.3.0-dist/css/bootstrap.min.css">
<script src="frameworks/jquery-2.1.4/jquery.min.js"></script>
<script src="frameworks/bootstrap-3.3.0-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/activity.css">
<link rel="stylesheet" type="text/css" href="css/myMusic.css">
<script type="text/javascript" src="./libs/jquery-3.1.1.min.js"></script>
<!-- 样式 -->
<style type="text/css">
	.music_play{
		width:100%;
		height:auto;
		background-color: #2d2d2d;
		border-top: 1px solid #4a4a4a;
		border-bottom:1px solid #4a4a4a;
		position: fixed;
		bottom:0%;
	}
	
	.current_div{
		height: 50px;;
		width: 428px;
		float: left;
		
	}
	.buttonOfMusic{
		height: 50px;
		width: 350px;
		float: left;
	}
	.buttonOfMusic .buttonLeft{
		width: 30px;
		height:50px; 
		float:right;
		line-height: 80px;
	
	}
	
	.buttonOfMusic .buttonStop{
		width: 50px;
		height:50px; 
		float:right;
		line-height: 80px;
	}
	
	.buttonOfMusic .buttonRight{
		width: 30px;
		height:50px; 
		float:right;
		line-height: 80px;
	}
	
	.current_div_1{
		height: 50px;
		width:50px;
		float:left;
		margin-left: 28px;
	}
	 span{
		line-height: 50px;
		color: #d4d4d4;
	}
	.current_div span{
		margin-right: 0px;
		
	}
	.total_div{
		height: 50px;
		width: 400px;
		float: left;
		margin-left:10px;
		background-color: #2d2d2d;
	}
	.player{
		width: 510px;
		height: auto;
		background-color: #2d2d2d;
		float:left;
	}
	.slider{
		width:500px;
		height: 10px;
		background-color: #181818;
		border-top: 1px solid #0b0b0b;
		border-bottom: 1px solid #4a4a4a;
		border-radius:6px;
		margin: 20px auto;
		position: relative;
	}
	.slider > .buffer{
		width:60%;
		height: 100%;
		background-color: #535353;
		border-radius:6px;
		position: absolute; 
	}
	.slider > .processor{
		width: 16px;
		height: 100%;
		background-color: #c70c0c;
		border-radius:16px;
		position: absolute;
	}
	.slider > .controller{
		position:absolute;
		width:6px;
		height:6px;
		border:10px solid #f3f3f6;
		background-color: rgba(255,255,255,0);
		-webkit-border-radius:50%;
		-moz-border-radius:50%;
		border-radius:50%;
		top:-6px;
		left:0px;
	}
	
</style>
</head>
<script type="text/javascript">
	
	$(function(){
		var $input = $('.createInput input')
		$('.createInput input:eq(1)').on('click',function(){
			if($input.val()==""){
				alert("歌单名字不能为空")
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
				<li><a href="toMyMusic.action" id="mymusic">我的音乐</a></li>
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
		</div>
	</div>
	<!-- 二级导航--红线 -->
	<div class="secondRedLine"></div>
	<!-- 内容 -->
	<div class="musicBody">
		<div class="musicBox">
			<div class="leftBox_1">
				<div class="createSheet">
					<div class="buttonOfCreate">
						<div class="createText">
							<p>创建</p>
						</div>
						<div class="createButton">
							<input type="image" src="./images/createButton.png" onclick="document.getElementById('createDiv').style.display='block'">
						</div>
						<div class="createText">
							<p>删除</p>
						</div>
						<div class="deleteButton">
							<input type="image" src="./images/deleteButton.png">
						</div>
					</div>
				</div>
				<div class="sheet">
					<div>
						<c:forEach items="${songSheetList}" var="sheet">
							<div class="sheet_1_logo">
								<img src="./images/myMusicLogo.png">
							</div>
							<div class="sheet_1_text">
								<a href="changeSheet.action?id=${sheet.id}">${sheet.sheetName}</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="rightBox_1">
				<div class="operate">
					<div class="operateLeft"></div>
					<div class="operateRight">
						<div class="operateRight_1">
							<div class="songSheetLogo">
								<img src="./images/songSheetButton.png">
							</div>
							<div class="songSheetName">
								${defautSheet.sheetName}
							</div>
						</div>
						<div class="operateRight_2">
							<div class="myMusic_userLogo">
								<img src="./images/myMusic_userLogo.png">
							</div>
							<div class="userName">
								<a href="#">${member.username}</a>
							</div>
							<div class="createTime">
								${defautSheet.createDate}&nbsp;&nbsp;创建
							</div>
						</div>
						<div class="operateRight_3">
							<div class="playMusic">
								<input type="image" src="./images/playMusicButton.png">
							</div>
							<div class="other">
								<img src="./images/navButton.png">
							</div>
						</div>
						<div class="createDiv" id="createDiv">
							<div class="blackBlock"></div>
							<div class="createInput">
								<form action="createSheet.action" method="post">
									歌单名称：<input type="text" name="sheetName"><br><br>
									<input type="image" src="./images/createButton_2.png" onclick="document.getElementById('createDiv').style.display='none'" >
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="myMusicNav"></div>
				<div class="redLine"></div>
				<div class="musicList">
					<table>
							<thead>
								<tr>
									<th>&nbsp;&nbsp;</th>
									<th>操作</th>
									<th>音乐标题</th>
									<th>歌手</th>
									<th>专辑</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody class="infolist">
								<c:forEach items="${musicByUser}" var="music">
									<tr>
										<td>&nbsp;&nbsp;</td>
										<td>播放</td>
										<td>${music.musicName}</td>
										<td>${music.album.singer}</td>
										<td>${music.album.albumName}</td>
										<td class="deleteMusic">
											<a href="removeMusic.action?sheetid=${defautSheet.id}&musicid=${music.id}"><img src="./images/deleteButton.png"></img></a>
										</td>
									</tr>	
								</c:forEach>
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部 -->
<div class="bottom"></div>

<!-- 进度 -->
<div class="schedule">
	<div class="music_play">
		<!-- 当前时间的div -->
		<div class="current_div">
			<div class="buttonOfMusic">
				<div class="buttonLeft">
					<input type="image" src="./images/nextButton.png">
				</div>
				<div class="buttonStop">
					<input type="image" src="./images/stopButton.png">
				</div>
				<div class="buttonRight">
					<input type="image" src="./images/preButton.png">
				</div>
			</div>
			<div class="current_div_1">
				<span class='currentTime'>00:00</span>
			</div>
		</div>
		<div class="player">
			<audio id="audio" src="" autoplay></audio>
 			<div class="slider">
 				<!-- <div class="buffer"></div> -->
 				<div class="processor"></div>
 				<div class="controller"></div>
 			</div>
 		</div>
 		<!-- 总时间的div -->
 		<div class="total_div">
 			<span class='totalTime'></span>
 		</div>
	</div>
</div><!-- 进度结束 -->

<!-- 模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择要删除的歌单</h4>
			</div>
			<c:forEach items="${songSheetList}" var="sheetList">
				<div class="modal-body">
					<input type='hidden' value='${sheetList.id}'/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">${sheetList.sheetName}</a>
				</div>
			</c:forEach>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
</body>
<script type="text/javascript">
	
	$(function(){
	    var $audio = $('audio'); 
	    //点击歌曲列表播放音乐 
		$('.infolist tr').on('click',function(){
			var $musicName = $(this).find('td').eq(2).text();
			var $src = "./music/"+$musicName+".mp3";
			$audio.attr('src',$src);
		})
		
		//点击删除歌单 
		$('.deleteButton input').on('click',function(){
			$('#myModal').attr('class', "modal show");
		})
		//点击触发加入歌单的a标签 
		$('.modal-body').on('click', function() {
			var $a2 = $(this).find("input[type='hidden']").val();
			var $href = "deleteSheet.action?&id="+$a2;
			$(this).find('a').attr('href', $href);
		})
		//点击关闭隐藏模态框 
		$('.modal-footer button').on('click', function() {
			$('#myModal').attr('class', "modal flad");
		})
		
		
		//切换进度条的状态 
		$(document).mouseover(function(e){
			var Y = e.pageY;
			var height = $(window).height();
			if(height-Y<120){
				$('.schedule').show(1000);
			}else{
				$('.schedule').hide(1000);
			}
		})
	});
	
	
	var slider = document.querySelector(".slider");
	//缓存 
	var buffer = document.querySelector(".buffer");
	//控制器 
	var controller = document.querySelector(".controller");
	//当前播放的长度
	var processor = document.querySelector(".processor");
	var player = document.getElementById("audio");
	//当前时间 
	var currentTimeOfNow = document.querySelector(".currentTime");
	//总时间 
	var totalTimeofNow = document.querySelector(".totalTime");
	player.onplaying = function(){
		var timeEnd = formatSeconds(player.duration);
		totalTimeofNow.innerText = timeEnd;
		setInterval(function(){
			var timeNow = formatSeconds(player.currentTime);
			currentTimeOfNow.innerText = timeNow;
			//获取进度比 
			var schedule = player.currentTime/player.duration;
			//改变进度控制器的位置 
			controller.style.left=schedule*484+"px";
			processor.style.width=schedule*490+16+"px";
		},1000);
		
	}
	//时间处理 
	function formatSeconds(value) {
	    value = parseInt(value);
	    var time;
	    if (value > -1) {
	        min = Math.floor(value / 60) % 60;
	        sec = value % 60;
	        if (min < 10) {
	            time = "0";
	        }
	        time += min + ":";
	        if (sec < 10) {
	            time += "0";
	        }
	        time += sec;
	    }
	    return time;
	}
	
</script>

	<c:if test="${!empty requestScope.message}">
		<script type="text/javascript">
			var msg = "${requestScope.message}";
			alert(msg);
		</script>
	</c:if>
</html>