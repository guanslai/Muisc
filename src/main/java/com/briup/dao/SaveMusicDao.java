package com.briup.dao;

import com.briup.bean.Album;
import com.briup.bean.MusicInfo;

public interface SaveMusicDao {

	public void saveMusic(MusicInfo music);
	
	public Album findAlbumById(Long id);
}
