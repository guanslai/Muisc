package com.briup.service;

import java.util.List;
import java.util.Set;

import com.briup.bean.Album;
import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.bean.SongSheet;
import com.briup.common.exception.MemberServiceException;

public interface MemberService {

	//注册
	public void register(MemberInfo memberinfo) throws MemberServiceException;
	//根据用户名字查询用户信息
	public MemberInfo findMemberInfoByName(String username) throws MemberServiceException;
	//登录
	public List<MusicInfo> findMusic(Set<SongSheet> songSheet) throws MemberServiceException;
	public MemberInfo login(MemberInfo memberInfo) throws MemberServiceException;
	//
	public void saveAlbum(Album album);
}
