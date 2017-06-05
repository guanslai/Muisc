package com.briup.service;

import java.util.List;

import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.common.exception.MusicServiceException;

public interface MusicService {

	//热门音乐
	public List<MusicInfo> hitSingles() throws MusicServiceException;
	//新碟上架
	public List<MusicInfo> newAlbum() throws MusicServiceException;
	//云音乐新歌榜
	public List<MusicInfo> newMusic() throws MusicServiceException;
	//原创歌曲榜
	public List<MusicInfo> originalMusic() throws MusicServiceException;
	//云音乐飙升榜
	public List<MusicInfo> soaringMusic() throws MusicServiceException;
	//将用户加入歌单的音乐写入文件
	public void writeToFile(MemberInfo memberInfo,MusicInfo musicInfo) throws MusicServiceException;
	//根据语言查询歌曲信息
	public List<MusicInfo> secByLanguage(MusicInfo musicInfo) throws MusicServiceException;
	//根据音乐的类型来查询音乐信息
	public List<MusicInfo> secByType(MusicInfo musicInfo) throws MusicServiceException;
	//根据音乐风格来查询音乐信息
	public List<MusicInfo> secByStyle(MusicInfo musicInfo) throws MusicServiceException;
}
