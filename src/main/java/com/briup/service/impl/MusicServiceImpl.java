package com.briup.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.common.exception.DataAccessException;
import com.briup.common.exception.MusicServiceException;
import com.briup.common.util.WriteToFile;
import com.briup.dao.MusicDao;
import com.briup.service.MusicService;

@Service
@Transactional
public class MusicServiceImpl implements MusicService {

	@Autowired
	private MusicDao musicDao;
	
	/*
	 * 热门音乐：
	 * 1.查询出音乐信息
	 * 2.根据音乐当前点播次数排序
	 * 
	 * */
	@Override
	public List<MusicInfo> hitSingles() throws MusicServiceException {
		List<MusicInfo> hitSingles = null;
		try {
			hitSingles = musicDao.hitSingles();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return hitSingles;
	}

	/*
	 * 新碟上架
	 * 将查询出来的数据进行排序
	 * 
	 * */
	@Override
	public List<MusicInfo> newAlbum() throws MusicServiceException {
		List<MusicInfo> album = null;
		try {
			album = musicDao.newAlbum();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		Collections.sort(album,new Comparator<MusicInfo>() {
			@Override
			public int compare(MusicInfo m1, MusicInfo m2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = null; 
				Date dt2 = null;
				try {
					dt1 = format.parse(m1.getAlbum().getReleaseDate().toString());
					dt2 = format.parse(m2.getAlbum().getReleaseDate().toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(dt1.getTime()>dt2.getTime()){
					return -1;
				} else if( dt1.getTime()<dt2.getTime()){
					return 1;
				}else{
					return 0;
				}
			}
		});
		return album;
	}
	
	/*
	 * 云音乐新歌榜
	 * 1.将一个月以外的歌曲排除
	 * 2.根据点播次数进行排序
	 * 
	 * */
	@Override
	public List<MusicInfo> newMusic() throws MusicServiceException {
		List<MusicInfo> newAlbum = null;
		long date = new Date().getTime();
		try {
			newAlbum = musicDao.newAlbum();
			//将1年前的歌曲去除
			for(int i=newAlbum.size()-1;i>=0;i--){
				long time = newAlbum.get(i).getAlbum().getReleaseDate().getTime();
				
				int a = (int) ((date-time)/(24*60*60*1000));
				if(a>365){
					newAlbum.remove(i);
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 
		//排序
		Collections.sort(newAlbum, new Comparator<MusicInfo>() {
			@Override
			public int compare(MusicInfo m1, MusicInfo m2) {
				if(m1.getTimes()>m2.getTimes()){
					return 1;
				}else if(m1.getTimes()<m2.getTimes()){
					return -1;
				}else{
					return 0;
				}
			}
		});
		return newAlbum;
	}

	/*
	 * 原创歌曲榜
	 * 1.查找出原创的歌曲
	 * 2.根据播放次数进行排序
	 * 
	 * */
	@Override
	public List<MusicInfo> originalMusic() throws MusicServiceException {
		List<MusicInfo> originalMusic = null;
		try {
			originalMusic = musicDao.originalMusic();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		Collections.sort(originalMusic, new Comparator<MusicInfo>() {
			@Override
			public int compare(MusicInfo m1, MusicInfo m2) {
				if(m1.getTimes()>m2.getTimes()){
					return -1;
				}else if(m1.getTimes()<m2.getTimes()){
					return 1;
				}else{
					return 0;
				}
			}
		});
		return originalMusic;
	}

	/*
	 * 云音乐飙升榜
	 * 1.查询出所有音乐
	 * 2.根据当前播放次数和历史播放次数进行差运算，增长的快的排在前面
	 * 
	 * 
	 * */
	@Override
	public List<MusicInfo> soaringMusic() throws MusicServiceException {
		List<MusicInfo> hitSingles = null;
		try {
			hitSingles = musicDao.hitSingles();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		Collections.sort(hitSingles, new Comparator<MusicInfo>() {

			@Override
			public int compare(MusicInfo m1, MusicInfo m2) {
				if((m1.getTimes()-m1.getHistorytimes())>(m2.getTimes()-m2.getHistorytimes())){
					return -1;
				}else if((m1.getTimes()-m1.getHistorytimes())<(m2.getTimes()-m2.getHistorytimes())){
					return 1;
				}else{
					return 0;
				}
			}
		});
		return hitSingles;
	}
	
	/*
	 * 将点击播放音乐用户的id和音乐的id按一定格式写入文件中
	 * */
	@Override
	public void writeToFile(MemberInfo memberInfo,MusicInfo musicInfo) throws MusicServiceException {
		String str1 = memberInfo.getId().toString();
		String str2 = musicInfo.getId().toString();
		String str = str1+","+str2;
		WriteToFile.write(str);
	}

	/*
	 * 根据语言查询歌曲信息
	 * 
	 * */
	@Override
	public List<MusicInfo> secByLanguage(MusicInfo musicInfo) throws MusicServiceException {
		List<MusicInfo> list = null;
		try {
			list = musicDao.secByLanguage(musicInfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 根据音乐的类型来查询音乐的信息
	 * 
	 * */
	@Override
	public List<MusicInfo> secByType(MusicInfo musicInfo) throws MusicServiceException {
		List<MusicInfo> list = null;
		try {
			list = musicDao.secByType(musicInfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 根据音乐风格查询音乐信息
	 * */
	@Override
	public List<MusicInfo> secByStyle(MusicInfo musicInfo) throws MusicServiceException {
		List<MusicInfo> list = null;
		try {
			list = musicDao.secByStyle(musicInfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

}
