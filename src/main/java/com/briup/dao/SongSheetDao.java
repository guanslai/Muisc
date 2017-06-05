package com.briup.dao;

import java.util.List;

import com.briup.bean.MusicInfo;
import com.briup.bean.Relation;
import com.briup.bean.SongSheet;
import com.briup.common.exception.DataAccessException;

public interface SongSheetDao {
	//根据用户ID查询用户歌单
	public List<SongSheet> findSheetByUserId(Long userId) throws DataAccessException;
	//往关系表添加信息
	public void saveRelation(Relation relation) throws DataAccessException;
	//创建歌单
	public void createSheet(SongSheet songSheet) throws DataAccessException;
	//根据歌单id获取音乐ID
	public List<Relation> findMusicIds(Long id) throws DataAccessException; 
	//根据音乐的Id查询出音乐信息
	public List<MusicInfo> findMusics(List<Relation> musicIds) throws DataAccessException;
	//根据歌单Id查询出歌单信息
	public SongSheet findSheetBySheetId(Long sheetId) throws DataAccessException;
	//往歌单表里添加音乐信息
	public void addMuisc(Relation relation) throws DataAccessException;
	//将音乐信息移除歌单
	public void removeMusic(Long muiscid, Long sheetid) throws DataAccessException;
	//删除歌单信息
	public void deleteSheet(SongSheet songSheet) throws DataAccessException;
}
