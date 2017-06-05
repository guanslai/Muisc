package com.briup.dao;

import java.util.List;

import com.briup.bean.MusicInfo;
import com.briup.common.exception.DataAccessException;

public interface MusicDao {
	//热门音乐
	public List<MusicInfo> hitSingles() throws DataAccessException;
	//新碟上架
	public List<MusicInfo> newAlbum() throws DataAccessException;
	//原创歌曲
	public List<MusicInfo> originalMusic() throws DataAccessException;
	//根据音乐Id查询音乐信息
	public MusicInfo findMusicById(Long musicId) throws DataAccessException;
	//根据语言查询歌曲信息
	public List<MusicInfo> secByLanguage(MusicInfo muiscInfo) throws DataAccessException;
	//根据音乐类型查询音乐信息
	public List<MusicInfo> secByType(MusicInfo musicInfo) throws DataAccessException;
	//根据音乐风格查询音乐信息
	public List<MusicInfo> secByStyle(MusicInfo musicInfo) throws DataAccessException;
}
