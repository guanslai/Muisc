<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加数据</title>
</head>
<!-- 用于插入音乐信息，与整个项目目前没有关系 -->
<body>
	<form action="saveAlbum.action" method="post">
		专辑名：<input type="text" name="albumName"><br><br>
		发布时间：<input type="text" name="releasedate"><br><br>
		歌手：<input type="text" name="singer"><br><br>
		<input type="submit" value="提交">
	</form>
	<br><br><br>
	
	<form action="saveMusic.action" method="post">
		音乐名：<input type="text" name="musicName"><br><br>
		路径：<input type="text" name="path"><br><br>
		历史播放次数：<input type="text" name="historytimes"><br><br>
		当前播放次数：<input type="text" name="times"><br><br>
		标签：<input type="text" name="label"><br><br>
		类型：<input type="text" name="type"><br><br>
		风格：<input type="text" name="style"><br><br>
		区域：<input type="text" name="area"><br><br>
		专辑:<input type="text" name="albu"><br><br>
		<input type="submit" value="提交">
		
	</form>
</body>
</html>