package com.briup.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Album;
import com.briup.bean.MemberInfo;
import com.briup.bean.MusicInfo;
import com.briup.bean.Relation;
import com.briup.bean.SongSheet;
import com.briup.common.exception.DataAccessException;
import com.briup.common.exception.MemberServiceException;
import com.briup.common.util.MD5;
import com.briup.common.util.Util;
import com.briup.dao.MemberDao;
import com.briup.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	/*
	 * 
	 * 注册
	 * 1.判断用户名是否存在，如果存在抛出异常
	 * 2.将现在的时间作为注册时间
	 * 3.保存到数据库
	 * 
	 * */
	@Override
	public void register(MemberInfo memberinfo) throws MemberServiceException {
		MemberInfo findMemberInfoByName = this.findMemberInfoByName(memberinfo.getUsername());
		if(findMemberInfoByName!=null){
			throw new MemberServiceException("用户名已经被注册");
		}
		try {
			memberinfo.setRegisterdate(new Date());
			memberinfo.setPassword(MD5.getMD5Str(memberinfo.getPassword()));
			memberDao.save(memberinfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 根据用户名字，查询用户信息
	 * 
	 * */
	
	@Override
	public MemberInfo findMemberInfoByName(String username) throws MemberServiceException {
		MemberInfo findMemberInfoByName = null;
		try {
			findMemberInfoByName = memberDao.findMemberInfoByName(username);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return findMemberInfoByName;
	}

	/*
	 * 用户名密码登陆
	 * 1.判断用户名是否存在，如果不存在抛出异常
	 * 2.判断密码是否正确，不正确抛出异常
	 * 3.如果都正确，查询出该用户播放过的音乐集合，使用协同过滤，给出一个推荐的歌曲列表
	 * 		注意：查询数据库的可能会打乱顺序，需要合理的使用数据库查询方法
	 * 
	 * */
	@Override
	public List<MusicInfo> findMusic(Set<SongSheet> songSheet) throws MemberServiceException {
		List<Long> findMusicId = new ArrayList<Long>();
		List<MusicInfo> findMusic = null;
		List<MusicInfo> listOfMusic = new ArrayList<MusicInfo>();
		List<Relation> relations = null;
		
		List<Long> list = new ArrayList<Long>();
		//将歌单的音乐
		for(SongSheet sheet:songSheet){
			list.add(sheet.getId());
		}
		try {
			if(list.size()!=0){
				//查询出这个用户听过的所有歌曲
				 relations = memberDao.findMusicId(list);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		//如果这个用户没有听过歌
		if(relations==null||relations.size()==0){
			return null;
		}else{
			//当前用户听过歌的id
			Collection<String> collection = new ArrayList<String>();
			//给当前用户推荐的歌曲的id集合
			Collection<String> value = new ArrayList<String>();
			for(Relation relation:relations){
				String str = relation.getMusics().getId().toString();
				collection.add(str);
			}
			//根据协同过滤查询出推荐的音乐id
			value = Util.getValue(collection);
			//如果有推荐值，则查询出信息（因为有可能这首歌第一次出现）
			if(value!=null){
				for(String str:value){
					findMusicId.add(Long.parseLong(str));
				}
				try {
					//查询出推荐的音乐信息，因为是乱序的，应和value里的顺序对应
					findMusic = memberDao.findMusic(findMusicId);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
			}
			
			//排序，使得findMusic的顺序和value的一致
			Iterator<String> iterator = value.iterator();
			while(iterator.hasNext()){
				String str = iterator.next();
				for(int i=0;i<findMusic.size();i++){
					if(str.equals(findMusic.get(i).getId().toString())){
						listOfMusic.add(findMusic.get(i));
					}
				}
			}
			return listOfMusic;
		}
	}
	
	/*
	 * 测试用 ：将专辑保存到数据库
	 * */
	@Override
	public void saveAlbum(Album album) {
		memberDao.saveAlbum(album);
	}

	@Override
	public MemberInfo login(MemberInfo memberInfo) throws MemberServiceException {
		MemberInfo findMemberInfoByName = this.findMemberInfoByName(memberInfo.getUsername());
		if(findMemberInfoByName==null){
			throw new MemberServiceException("用户不存在");
		}
		if(!findMemberInfoByName.getPassword().equals(MD5.getMD5Str(memberInfo.getPassword()))){
			throw new MemberServiceException("密码不正确");
		}
		return findMemberInfoByName;
	}

	
	

}
