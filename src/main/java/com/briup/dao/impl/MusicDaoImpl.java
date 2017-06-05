package com.briup.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.briup.bean.Album;
import com.briup.bean.MusicInfo;
import com.briup.common.exception.DataAccessException;
import com.briup.dao.MusicDao;

@Repository
public class MusicDaoImpl implements MusicDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	//创建session
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	/*
	 * 热门音乐
	 * 1.查询数据库，降序排列
	 * 
	 * */
	@Override
	public List<MusicInfo> hitSingles() throws DataAccessException {
		String hql = "from MusicInfo order by times desc";
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/*
	 * 新碟上架
	 * 
	 * */
	@Override
	public List<MusicInfo> newAlbum() throws DataAccessException {
		
		List<Album> albums = this.albumid();
		String hql = "from MusicInfo where ";
		for(int i=0;i<albums.size();i++){
			if(i==0){
				hql = hql+"albumid="+albums.get(i).getId();
			}else{
				hql = hql + " or albumid="+albums.get(i).getId();
			}
		}
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = this.getSession().createQuery(hql).list();
		return list;
	}

	private List<Album> albumid(){
		String hql = "from Album order by releasedate desc";
		@SuppressWarnings("unchecked")
		List<Album> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/*
	 * 
	 * 原创歌曲
	 * 根据：label=原创来查询数据库
	 * 
	 * */
	@Override
	public List<MusicInfo> originalMusic() throws DataAccessException {
		String hql = "from MusicInfo where label= '原唱' ";
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/*
	 * 根据音乐id查找音乐信息
	 * 
	 * */
	@Override
	public MusicInfo findMusicById(Long musicId) throws DataAccessException {
		String hql = "from MusicInfo where id="+musicId;
		MusicInfo musicInfo = (MusicInfo) this.getSession().createQuery(hql).uniqueResult();
		return musicInfo;
	}

	/*
	 * 根据语言查询歌曲信息
	 * 
	 * */
	@Override
	public List<MusicInfo> secByLanguage(MusicInfo musicInfo) throws DataAccessException {
		String hql = "from MusicInfo where area=?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, musicInfo.getArea());
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = query.list();
		return list;
	}

	/*
	 * 根据类型查询歌曲信息
	 * */
	@Override
	public List<MusicInfo> secByType(MusicInfo musicInfo) throws DataAccessException {
		String hql = "from MusicInfo where type=?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, musicInfo.getType());
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = query.list();
		return list;
	}

	/*
	 * 根据风格查询歌曲信息
	 * */
	@Override
	public List<MusicInfo> secByStyle(MusicInfo musicInfo) throws DataAccessException {
		String hql = "from MusicInfo where style=?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, musicInfo.getStyle());
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = query.list();
		return list;
	}
}
