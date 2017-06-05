package com.briup.web.action;

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
import com.briup.common.exception.MemberServiceException;
import com.briup.common.exception.SongSheetServiceException;
import com.briup.service.MemberService;
import com.briup.service.SongSheetService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class MemberAction implements ModelDriven<MemberInfo>,SessionAware,ServletRequestAware{
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private SongSheetService songSheetService;
	
	private HttpServletRequest request;
	private MemberInfo memberInfo;
	
	private Map<String,Object> session;
	
	/*
	 * 
	 * 跳转到登陆页面
	 * 
	 * */
	@Action(value="toLogin",results={
			@Result(name="success",location="/WEB-INF/jsp/login.jsp")
	})
	public String toLogin(){
		return "success";
	}
	
	/*
	 * 
	 * 跳转到注册页面
	 * 
	 * */
	@Action(value="toRegister",results={
			@Result(name="success",location="/WEB-INF/jsp/register.jsp")
	})
	public String toRegister(){
		return "success";
	}
	/*
	 * 
	 * 注册 
	 * 
	 * */
	@Action(value="register",results={
			@Result(name="success",location="/WEB-INF/jsp/login.jsp")
	})
	public String register(){
		try {
			memberService.register(memberInfo);
		} catch (MemberServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/*
	 * 
	 * 登陆
	 * 将该用户的信息发送给service层，找出播放历史，为其推荐歌曲
	 * 
	 * */
	@Action(value="login",results={
			@Result(name="success",location="/WEB-INF/jsp/activity.jsp"),
			@Result(name="error",location="/WEB-INF/jsp/login.jsp")
	})
	public String login(){
		List<SongSheet> list = null;
		try {
			MemberInfo member = memberService.login(memberInfo);
			list = songSheetService.findSheetBymemberId(member.getId());
			//获得推荐的音乐
			List<MusicInfo> recommendMusic = memberService.findMusic(member.getSongSheet());
			if(recommendMusic!=null&&recommendMusic.size()>=1){
				for(int i=recommendMusic.size()-1;i>=0;i--){
					if(i>=3){
						recommendMusic.remove(i);
					}
				}
			}
			session.put("member", member);
			session.put("recommendMusic", recommendMusic);
			session.put("songSheetList", list);
		} catch (MemberServiceException e) {
			request.setAttribute("message", "用户名密码不正确");
			return "error";
		} catch (SongSheetServiceException e) {
			e.printStackTrace();
		}
		return "success";
	}

	/*
	 * 注销
	 * 将session的信息清空
	 * 
	 * */
	@Action(value="logout",results={
			@Result(name="success",location="/WEB-INF/jsp/index.jsp")
	})
	public String logout(){
		session.clear();
		return "success";
	}
	
	@Override
	public MemberInfo getModel() {
		memberInfo = new MemberInfo();
		return memberInfo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
