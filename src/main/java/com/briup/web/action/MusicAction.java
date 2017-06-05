package com.briup.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.common.exception.MusicServiceException;
import com.briup.common.exception.SongSheetServiceException;
import com.briup.service.MusicService;
import com.briup.service.SongSheetService;
import com.opensymphony.xwork2.ModelDriven;



@Controller
public class MusicAction implements ModelDriven<MusicInfo>,ServletContextAware,SessionAware{

	
	private ServletContext context;
	private Map<String, Object> session;
	private MusicInfo musicInfo;
	
	private Long musicid;//音乐ID
	private Long sheetid;//歌单名称
	private Long num;
	
	@Autowired
	private MusicService musicService;
	@Autowired
	private SongSheetService songSheetService;
	
	
	/*
	 * 进入主页面
	 * */
	@Action(value="toHome",results={
			@Result(name="success",location="/WEB-INF/jsp/homePage.jsp")
	})
	public String toHome(){
		return "success";
	}
	
	
	/*
	 * 进入首页
	 * 
	 * */
	
	@Action(value="toIndex",results={
			@Result(name="success",location="/WEB-INF/jsp/index.jsp")
	})
	public String toIndex(){
		try {
			//热门音乐
			List<MusicInfo> hitSingles = musicService.hitSingles();
			for(int i=hitSingles.size()-1;i>=0;i--){
				if(i>7){
					hitSingles.remove(i);
				}
			}
			//新专辑
			List<MusicInfo> newAlbum = musicService.newAlbum();
			for(int i=newAlbum.size()-1;i>=0;i--){
				if(i>4){
					newAlbum.remove(i);
				}
			}
			//新歌
			List<MusicInfo> newMusic = musicService.newMusic();
			for(int i=newMusic.size()-1;i>=0;i--){
				if(i>=10){
					newMusic.remove(i);
				}
			}
			//原创
			List<MusicInfo> originalMusic = musicService.originalMusic();
			for(int i=originalMusic.size()-1;i>=0;i--){
				if(i>=10){
					newMusic.remove(i);
				}
			}
			//飙升
			List<MusicInfo> soaringMusic = musicService.soaringMusic();
			for(int i=soaringMusic.size()-1;i>=0;i--){
				if(i>=10){
					newMusic.remove(i);
				}
			}
			context.setAttribute("hitSingles", hitSingles);
			context.setAttribute("newAlbum", newAlbum);
			context.setAttribute("newMusic", newMusic);
			context.setAttribute("originalMusic", originalMusic);
			context.setAttribute("soaringMusic", soaringMusic);
		} catch (MusicServiceException e) {
			e.printStackTrace();
		} 
		return "success";
	}
	
	/*
	 * 
	 * 点击添加歌单
	 * 1.根据点击的歌曲id和点击模态框选择的歌单名称，将信息存入数据库
	 * 2.将该用户添加的歌曲放入文件中
	 * */
	@Action(value="addMusic",results={
			@Result(name="success",location="/WEB-INF/jsp/activity.jsp")
	})
	public String addMusic(){
		try {
			MemberInfo member = (MemberInfo) session.get("member");
			songSheetService.addMuisc(musicid, sheetid);
			musicInfo.setId(musicid);
			System.out.println("test-----"+musicInfo.getId()+"------"+musicid+"------"+sheetid);
			musicService.writeToFile(member, musicInfo);
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	/*
	 * 将歌曲移出歌单
	 * 
	 * */
	@Action(value="removeMusic",results={
			@Result(name="success",location="/WEB-INF/jsp/mymusic.jsp")
	})
	public String removeMusic(){
		try {
			songSheetService.removeMusic(musicid, sheetid);
			@SuppressWarnings("unchecked")
			List<MusicInfo> list = (List<MusicInfo>) session.get("musicByUser");
			for(int i=list.size()-1;i>=0;i--){
				if(list.get(i).getId()==musicid){
					list.remove(i);
				}
			}
			session.put("musicByUser", list);
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/*
	 * 点击进入"发现音乐"
	 * 1.判断当前用户是否登录
	 * 2.如果用户已经登录----那么跳转到activity.jsp页面
	 * 3.如果用户没有登录----那么跳转带index.jsp页面
	 * 
	 * */
	@Action(value="toActivity",results={
			@Result(name="success",location="/WEB-INF/jsp/activity.jsp"),
			@Result(name="unsuccess",location="/WEB-INF/jsp/index.jsp")
	})
	public String toActivity(){
		MemberInfo member = (MemberInfo) session.get("member");
		if(member!=null){
			return "success";
		}else{
			return "unsuccess";
		}
	}
	/*
	 * 进入歌单页面
	 * 查询出全部的音乐信息，并将这些信息返回到页面 
	 * 以及查询的条件一起返回给页面，默认的是查询全部
	 * 
	 * */
	@Action(value="toMusicList",results={
			@Result(name="success",location="/WEB-INF/jsp/musicList.jsp")
	})
	public String toMusicList(){
		try {
			List<MusicInfo> list = musicService.hitSingles();
			session.put("listMusic", list);
			session.put("condition", "全部");
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/*
	 * 根据语言类型查询歌曲列表
	 * 
	 * */
	@Action(value="secByLanguage",results={
			@Result(name="success",location="/WEB-INF/jsp/musicList.jsp")
	})
	public String secByLanguage(){
		List<MusicInfo> list = null;
		try {
			list = musicService.secByLanguage(musicInfo);
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		session.put("listMusic", list);
		session.put("condition", musicInfo.getArea());
		return "success";
	}
	
	/*
	 * 根据音乐类型查询音乐信息
	 * 
	 * */
	@Action(value="secByType",results={
			@Result(name="success",location="/WEB-INF/jsp/musicList.jsp")
	})
	public String secByType(){
		List<MusicInfo> list = null;
		try {
			list = musicService.secByType(musicInfo);
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		session.put("condition", musicInfo.getType());
		session.put("listMusic", list);
		return "success";
	}
	
	/*
	 * 根据音乐的风格查询音乐信息
	 * */
	@Action(value="secByStyle",results={
			@Result(name="success",location="/WEB-INF/jsp/musicList.jsp")
	})
	public String secByStyle(){
		List<MusicInfo> list = null;
		try {
			list = musicService.secByStyle(musicInfo);
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		session.put("condition", musicInfo.getStyle());
		session.put("listMusic", list);
		return "success";
	}
	
	/*
	 * 点击跳转到排行榜页面
	 * 默认将飙升榜的音乐查询出来，放入application
	 * 
	 * */
	@Action(value="toRankList",results={
			@Result(name="success",location="/WEB-INF/jsp/rankList.jsp")
	})
	public String toRankList(){
		List<MusicInfo> soaringMusic = null;
		try {
			soaringMusic = musicService.soaringMusic();
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		context.setAttribute("rankList", soaringMusic);
		context.setAttribute("rankName", "云音乐飙升榜");
		return "success";
	}
	
	
	/*
	 * 排行榜的切换
	 * 根据前端传过来的数据进行判断用户要查询那个榜单
	 * 将所要查询的榜单获取，传给前端页面
	 * 
	 * */
	@Action(value="changeRank",results={
			@Result(name="success",location="/WEB-INF/jsp/rankList.jsp")
	})
	public String changeRank(){
		try {
			//新歌
			if(num==2){
				List<MusicInfo> newMusic = musicService.newMusic();
				context.setAttribute("rankList", newMusic);
				context.setAttribute("rankName", "云音乐新歌榜");
			}else if(num==3){
			//原创
				List<MusicInfo> originalMusic = musicService.originalMusic();
				context.setAttribute("rankList", originalMusic);
				context.setAttribute("rankName", "云音乐原创歌曲榜");
			}else if(num==4){
			//热歌
				List<MusicInfo> hitSingles = musicService.hitSingles();
				context.setAttribute("rankList", hitSingles);
				context.setAttribute("rankName", "云音乐热歌榜");
			}else{
				List<MusicInfo> soaringMusic = musicService.soaringMusic();
				context.setAttribute("rankList", soaringMusic);
				context.setAttribute("rankName", "云音乐飙升榜");
			}
		} catch (MusicServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/*
	 * 进入未完成页面
	 * */
	@Action(value="toUnfinish",results={
			@Result(name="success",location="/WEB-INF/jsp/unfinish.jsp")
	})
	public String toUnfinish(){
		return "success";
	}
	
	@Override
	public MusicInfo getModel() {
		musicInfo = new MusicInfo();
		return musicInfo;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	public Long getMusicid() {
		return musicid;
	}

	public void setMusicid(Long musicid) {
		this.musicid = musicid;
	}

	public Long getSheetid() {
		return sheetid;
	}

	public void setSheetid(Long sheetid) {
		this.sheetid = sheetid;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
}
