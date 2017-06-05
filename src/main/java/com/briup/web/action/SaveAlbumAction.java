package com.briup.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.briup.bean.Album;
import com.briup.service.MemberService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SaveAlbumAction implements ModelDriven<Album>{

	@Autowired
	private MemberService memberService;
	private Album album;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private String releasedate;
	
	/*
	 * 
	 * 进入插入专辑页面
	 * */
	@Action(value="toSave",results={
			@Result(name="success",location="/WEB-INF/jsp/saveDate.jsp")
	})
	public String toSave(){
		return "success";
	}

	@Action(value="saveAlbum",results={
			@Result(name="success",location="/WEB-INF/jsp/saveDate.jsp")
	})
	public String saveAlbum(){
		try {
			Date date = format.parse(releasedate);
			album.setReleaseDate(date);
			memberService.saveAlbum(album);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return "success";
	}
	
	@Override
	public Album getModel() {
		album = new Album();
		return album;
	}

	public String getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	
	
}
