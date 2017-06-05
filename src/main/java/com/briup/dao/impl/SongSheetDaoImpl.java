package com.briup.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.briup.bean.MusicInfo;
import com.briup.bean.Relation;
import com.briup.bean.SongSheet;
import com.briup.common.exception.DataAccessException;
import com.briup.dao.SongSheetDao;

@Repository
public class SongSheetDaoImpl implements SongSheetDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	//创建session
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	/*
	 * 根据用户信息查询歌单信息
	 * 
	 * */
	@Override
	public List<SongSheet> findSheetByUserId(Long userId) throws DataAccessException {
		String hql = "from SongSheet where memberid="+userId;
		@SuppressWarnings("unchecked")
		List<SongSheet> songSheet = (List<SongSheet>) this.getSession().createQuery(hql).list();
		return songSheet;
	}

	/*
	 * 往关系表添加信息
	 * 
	 * */
	@Override
	public void saveRelation(Relation relation) throws DataAccessException {
		this.getSession().save(relation);
	}

	/*
	 * 创建歌单
	 * */
	@Override
	public void createSheet(SongSheet songSheet) throws DataAccessException {
		this.getSession().save(songSheet);
	}

	/*
	 * 根据歌单id查询音乐id
	 * 
	 * */
	@Override
	public List<Relation> findMusicIds(Long id) throws DataAccessException {
		String hql = "from Relation where sheet_id="+id;
		@SuppressWarnings("unchecked")
		List<Relation> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/*
	 * 根据音乐ID查询出音乐信息
	 * 
	 * */
	@Override
	public List<MusicInfo> findMusics(List<Relation> musicIds) throws DataAccessException {
		String hql = "from MusicInfo where ";
		for(int i=0;i<musicIds.size();i++){
			if(i==0){
				hql = hql + "id="+musicIds.get(i).getMusics().getId();
			}else{
				hql = hql +" or id="+musicIds.get(i).getMusics().getId();
			}
		}
		@SuppressWarnings("unchecked")
		List<MusicInfo> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/*
	 * 根据歌单的id查询出歌单的信息
	 * 
	 * */
	@Override
	public SongSheet findSheetBySheetId(Long sheetId) throws DataAccessException {
		String hql = "from SongSheet where id="+sheetId;
		SongSheet songSheet = (SongSheet) this.getSession().createQuery(hql).uniqueResult();
		return songSheet;
	}

	/*
	 * 往歌单里添加音乐信息
	 * 
	 * */
	@Override
	public void addMuisc(Relation relation) throws DataAccessException {
		this.getSession().save(relation);
	}

	/*
	 * 将音乐信息移除歌单
	 * 
	 * */
	@Override
	public void removeMusic(Long muiscid, Long sheetid) throws DataAccessException {
		String hql = "delete Relation where music_id=? and sheet_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, muiscid);
		query.setLong(1, sheetid);
		query.executeUpdate();
	}

	/*
	 * 删除歌单信息
	 * 
	 * */
	@Override
	public void deleteSheet(SongSheet songSheet) throws DataAccessException {
		//删除关系表
		String hql = "delete Relation where sheet_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, songSheet.getId());
		query.executeUpdate();
		//删除歌单表
		String hql2 = "delete SongSheet where id=?";
		Query query2 = this.getSession().createQuery(hql2);
		query2.setLong(0, songSheet.getId());
		query2.executeUpdate();
		
	}

}
