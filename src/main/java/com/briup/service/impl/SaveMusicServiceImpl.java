package com.briup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Album;
import com.briup.bean.MusicInfo;
import com.briup.dao.SaveMusicDao;
import com.briup.service.SaveMusicService;

@Service
public class SaveMusicServiceImpl implements SaveMusicService{

	@Autowired
	private SaveMusicDao musicDao;
	
	@Override
	public void saveMusic(MusicInfo music, String albu) {
		Long id = Long.parseLong(albu);
		Album album = musicDao.findAlbumById(id);
		music.setAlbum(album);
		musicDao.saveMusic(music);
	}

	

	
}
