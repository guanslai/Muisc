package com.briup.web.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.bean.SongSheet;
import com.briup.common.exception.SongSheetServiceException;
import com.briup.service.SongSheetService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SongSheetAction implements ModelDriven<SongSheet>,SessionAware,ServletRequestAware{

	private Map<String, Object> session;
	private HttpServletRequest request;
	private SongSheet songSheet;
	@Autowired
	private SongSheetService songSheetService;
	
	/* 
	 * 创建歌单
	 * 	1.如果用户已经登录，那么就将歌单信息插入数据库
	 * 
	 * */
	@Action(value="createSheet",results={
			@Result(name="success",location="/WEB-INF/jsp/mymusic.jsp")
	})
	public String creatSheet(){
		MemberInfo member = (MemberInfo) session.get("member");
		List<SongSheet> list = null;
		songSheet.setCreateDate(new Date());
		songSheet.setMemberinfo(member);
		try {
			if(songSheet.getSheetName()==null||songSheet.getSheetName()==""){
				return "success";
			}
			//创建歌单
			songSheetService.createSheet(songSheet);
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		}
		try {
			list = songSheetService.findSheetBymemberId(member.getId());
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		}
		//更新歌单信息
		session.put("songSheetList", list);
		return "success";
	}
	
	/*
	 * 删除歌单，将歌单移除数据库 
	 * 
	 * */
	@Action(value="deleteSheet",results={
			@Result(name="success",location="/WEB-INF/jsp/mymusic.jsp")
	})
	public String deleteSheet(){
		try {
			songSheetService.deleteSheet(songSheet);
			@SuppressWarnings("unchecked")
			List<SongSheet> list = (List<SongSheet>) session.get("songSheetList");
			for(int i=list.size()-1;i>=0;i--){
				if(list.get(i).getId()==songSheet.getId()){
					list.remove(i);
				}
			}
			Long sheetId = 0L;
			List<MusicInfo> musicByUser = null;
			SongSheet defautSheet = null;
			for(int i=0;i<list.size();i++){
				if(i==0){
					defautSheet = list.get(i);
					sheetId = list.get(i).getId();
				}
			}
			musicByUser = songSheetService.findMusicBySheetId(sheetId);
			session.put("musicByUser", musicByUser);
			session.put("defautSheet",defautSheet);
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/*
	 * 进入我的音乐
	 * 1.查询出当前用户的歌单
	 * 2.查询出当前用户的默认歌单的歌曲，并存放到session中
	 * */
	@Action(value="toMyMusic",results={
			@Result(name="success",location="/WEB-INF/jsp/mymusic.jsp"),
			@Result(name="login",location="/WEB-INF/jsp/login.jsp")
	})
	public String toMyMusic(){
		MemberInfo member = (MemberInfo) session.get("member");
		//判断用户是否登录，如果没有登录抛出异常，并且跳转到登陆页面
		if(member==null){
			try {
				throw new Exception();
			} catch (Exception e) {
				request.setAttribute("message", "您还没登陆，赶紧去登陆吧");
				return "login";
			}
		}else{
			//查询歌单
			Long sheetId = 0L;
			@SuppressWarnings("unchecked")
			List<SongSheet> list = (List<SongSheet>) session.get("songSheetList");
			List<MusicInfo> musicByUser = null;
			SongSheet defautSheet = null;
			try {
				for(int i=0;i<list.size();i++){
					if(i==0){
						defautSheet = list.get(i);
						sheetId = list.get(i).getId();
					}
				}
				musicByUser = songSheetService.findMusicBySheetId(sheetId);
			} catch (SongSheetServiceException e) {
				e.printStackTrace();
			}
			session.put("musicByUser", musicByUser);
			session.put("defautSheet",defautSheet);
		}
		return "success";
	}
	/*
	 * 切换歌单
	 * 1.从页面获取到歌单的id，查询这个歌单的信息
	 * 2.查询这个歌单里包含的所有歌曲
	 * 3.将查询到的信息存入session中
	 * 
	 * */
	@Action(value="changeSheet",results={
			@Result(name="success",location="/WEB-INF/jsp/mymusic.jsp")
	})
	public String changeSheet(){
		SongSheet findSheetBySheetId = null;
		List<MusicInfo> findMusicBySheetId = null;
		try {
			findSheetBySheetId = songSheetService.findSheetBySheetId(songSheet.getId());
			findMusicBySheetId = songSheetService.findMusicBySheetId(songSheet.getId());
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		}
		//音乐信息
		session.put("musicByUser", findMusicBySheetId);
		//默认的歌单信息
		session.put("defautSheet",findSheetBySheetId);
		return "success";
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public SongSheet getModel() {
		songSheet = new SongSheet();
		return songSheet;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
