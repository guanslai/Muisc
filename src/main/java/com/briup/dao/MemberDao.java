package com.briup.dao;

import java.util.List;

import com.briup.bean.Album;
import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.bean.Relation;
import com.briup.common.exception.DataAccessException;

public interface MemberDao {
	//保存用户信息
	public void save(MemberInfo memberinfo) throws DataAccessException;
	//根据名字查询用户信息
	public MemberInfo findMemberInfoByName(String username) throws DataAccessException;
	//根据歌单的ID查询出歌曲的id
	public List<Relation> findMusicId(List<Long> list) throws DataAccessException;
	
	public List<MusicInfo> findMusic(List<Long> musicids)throws DataAccessException;
	//将专辑数据插入数据库
	public void saveAlbum(Album album);
}
