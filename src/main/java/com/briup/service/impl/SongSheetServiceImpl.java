package com.briup.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.MusicInfo;
import com.briup.bean.Relation;
import com.briup.bean.SongSheet;
import com.briup.common.exception.DataAccessException;
import com.briup.common.exception.SongSheetServiceException;
import com.briup.dao.MemberDao;
import com.briup.dao.MusicDao;
import com.briup.dao.SongSheetDao;
import com.briup.service.SongSheetService;

@Service
public class SongSheetServiceImpl implements SongSheetService{

	@Autowired
	public SongSheetDao songSheetDao;
	@Autowired
	public MusicDao musicDao;
	@Autowired
	public MemberDao memberDao;
	

	/*
	 * 创建歌单
	 * 
	 * */
	@Override
	public void createSheet(SongSheet songSheet) throws SongSheetServiceException {
		try {
			songSheetDao.createSheet(songSheet);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 根据用户id查询出歌单集合
	 * 1.排序，将id小的放前面
	 * 
	 * */
	@Override
	public List<SongSheet> findSheetBymemberId(Long memberId) throws SongSheetServiceException {
		List<SongSheet> sheetByUserId = null;
		try {
			sheetByUserId = songSheetDao.findSheetByUserId(memberId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		//排序，将id最小的作为默认歌单
		Collections.sort(sheetByUserId,new Comparator<SongSheet>() {

			@Override
			public int compare(SongSheet s1, SongSheet s2) {
				if(s1.getId()>s2.getId()){
					return 1;
				}else if(s1.getId()<s2.getId()){
					return -1;
				}else{
					return 0;
				}
			}
		});
		return sheetByUserId;
	}


	/*
	 * 根据歌单的id查询出这个歌单里存放的所有音乐信息
	 * 返回给WEB层
	 * 1.查询这个歌单是否有音乐id，如果有则将这些音乐信息查询出来
	 * 2.如果没有就返回null
	 * 
	 * */
	@Override
	public List<MusicInfo> findMusicBySheetId(Long sheet_id) throws SongSheetServiceException {
		List<Relation> findMusics = null;
		List<MusicInfo> musics = null;
		try {
			findMusics = songSheetDao.findMusicIds(sheet_id);
			//如果歌单里有音乐，那么查询出音乐信息
			if(findMusics.size()!=0){
				musics = songSheetDao.findMusics(findMusics);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return musics;
	}

	/*
	 * 根据歌单id查询出这个歌单的所有信息
	 * 
	 * */
	@Override
	public SongSheet findSheetBySheetId(Long sheet_id) throws SongSheetServiceException {
		SongSheet songSheet = null;
		try {
			songSheet = songSheetDao.findSheetBySheetId(sheet_id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return songSheet;
	}

	/*
	 * 1.往歌单里添加音乐信息
	 * 
	 * */
	@Override
	public void addMuisc(Long musicid, Long sheetid) throws SongSheetServiceException {
		SongSheet songSheet = null;
		MusicInfo musicInfo = null;
		try {
			songSheet = songSheetDao.findSheetBySheetId(sheetid);
			musicInfo = musicDao.findMusicById(musicid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		Relation relation = new Relation();
		relation.setMusics(musicInfo);
		relation.setSongSheet(songSheet);
		
		try {
			songSheetDao.addMuisc(relation);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 将歌曲移出歌单
	 * 
	 * */
	@Override
	public void removeMusic(Long muiscid, Long sheetid) throws SongSheetServiceException {
		try {
			songSheetDao.removeMusic(muiscid, sheetid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 删除歌单，将歌单的信息移除数据库
	 * 
	 * */
	@Override
	public void deleteSheet(SongSheet songSheet) throws SongSheetServiceException {
		try {
			songSheetDao.deleteSheet(songSheet);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	

}
