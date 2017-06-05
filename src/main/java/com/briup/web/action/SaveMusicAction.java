package com.briup.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.briup.bean.MusicInfo;
import com.briup.service.SaveMusicService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SaveMusicAction implements ModelDriven<MusicInfo>{


	@Autowired
	private SaveMusicService musicService;
	
	private MusicInfo musicInfo;
	private String albu;
	
	@Action(value="saveMusic",results={
			@Result(name="success",location="/WEB-INF/jsp/saveDate.jsp")
	})
	public String saveMusic(){
		System.out.println(albu);
		System.out.println("test--------"+musicInfo.toString());
		musicService.saveMusic(musicInfo, albu);
		return "success";
	}
	
	
	@Override
	public MusicInfo getModel() {
		this.musicInfo = new MusicInfo();
		return musicInfo;
	}


	public String getAlbu() {
		return albu;
	}


	public void setAlbu(String albu) {
		this.albu = albu;
	}
	
	
	
}
