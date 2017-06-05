package com.briup.service;

import java.util.List;

import com.briup.bean.MusicInfo;
import com.briup.bean.SongSheet;
import com.briup.common.exception.SongSheetServiceException;

public interface SongSheetService {

	//创建歌单
	public void createSheet(SongSheet songSheet) throws SongSheetServiceException;
	//根据用户id查询歌单信息
	public List<SongSheet> findSheetBymemberId(Long memberId) throws SongSheetServiceException;
	//根据用户Id查询用户播放过的歌曲
	public List<MusicInfo> findMusicBySheetId(Long sheet_id) throws SongSheetServiceException;
	//根据歌单id查询歌单信息
	public SongSheet findSheetBySheetId(Long sheet_id) throws SongSheetServiceException;
	//往歌单添加歌曲
	public void addMuisc(Long musicid,Long sheetid) throws SongSheetServiceException;
	//将歌曲移出歌单
	public void removeMusic(Long muiscid,Long sheetid) throws SongSheetServiceException;
	//删除歌单
	public void deleteSheet(SongSheet songSheet) throws SongSheetServiceException;
}
