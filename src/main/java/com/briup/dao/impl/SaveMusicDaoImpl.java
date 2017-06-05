package com.briup.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.briup.bean.Album;
import com.briup.bean.MusicInfo;
import com.briup.dao.SaveMusicDao;

@Repository
public class SaveMusicDaoImpl implements SaveMusicDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	//创建session
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	@Override
	public void saveMusic(MusicInfo music) {
		this.getSession().save(music);
	}

	@Override
	public Album findAlbumById(Long id) {
		Album album = null;
		String hql = "from Album where id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setLong("id", id);
		@SuppressWarnings("unchecked")
		List<Album> list = query.list();
		for(Album a:list){
			album = a;
		}
		return album;
	}
	
}
