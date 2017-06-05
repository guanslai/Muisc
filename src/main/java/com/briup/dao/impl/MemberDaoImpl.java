package com.briup.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.briup.bean.Album;
import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.bean.Relation;
import com.briup.common.exception.DataAccessException;
import com.briup.dao.MemberDao;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	//创建session
	public Session getSession(){
		return sessionFactory.openSession();
	}
	/*
	 * 
	 * 将用户信息插入数据库
	 * 
	 * */
	@Override
	public void save(MemberInfo memberinfo) throws DataAccessException {
		this.getSession().save(memberinfo);
	}

	/*
	 * 
	 * 根据名字查询用户信息
	 * 
	 * */
	@Override
	public MemberInfo findMemberInfoByName(String username) throws DataAccessException {
		MemberInfo memberinfo = null;
		String hql = "from MemberInfo where username="+"'"+username+"'";
		@SuppressWarnings("unchecked")
		List<MemberInfo> createQuery = this.getSession().createQuery(hql).list();
		for(MemberInfo m:createQuery){
			memberinfo = m;
		}
		return memberinfo;
	}
	/*
	 * 根据歌单的id查询出这个用户听过的歌曲的id
	 * 
	 * */
	@Override
	public List<Relation> findMusicId(List<Long> list) throws DataAccessException {
		String hql = " from Relation where ";
		for(int i=0;i<list.size();i++){
			if(i==0){
				hql = hql + "sheet_id="+list.get(i);
			}else{
				hql = hql + " or sheet_id="+list.get(i);
			}
		}
		@SuppressWarnings("unchecked")
		List<Relation> list2 = this.getSession().createQuery(hql).list();
		return list2;
	}
	
	/*
	 * 根据音乐的id查询出音乐信息
	 * 
	 * */
	@Override
	public List<MusicInfo> findMusic(List<Long> musicids) throws DataAccessException {
		String hql = "from MusicInfo where ";
		for(int i=0;i<musicids.size();i++){
			if(i==0){
				hql = hql + "id="+musicids.get(i);
			}else{
				hql = hql + " or id="+musicids.get(i);
			}
		}
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	/*
	 * 
	 * 
	 * */
	@Override
	public void saveAlbum(Album album) {
		this.getSession().save(album);
	}
	
	
}
