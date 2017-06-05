package com.briup.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 
 * 好友类
 * 
 * */

@Entity
@Table(name="friend_record")
public class FriendRecord {

	private Long id;
	private String selfName;//用户自己的名字
	private String friendName;//朋友名字
	public FriendRecord() {
	}
	public FriendRecord(Long id, String selfName, String friendName) {
		super();
		this.id = id;
		this.selfName = selfName;
		this.friendName = friendName;
	}
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="self_name")
	public String getSelfName() {
		return selfName;
	}
	public void setSelfName(String selfName) {
		this.selfName = selfName;
	}
	@Column(name="friend_name")
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	@Override
	public String toString() {
		return "FriendRecord [id=" + id + ", selfName=" + selfName + ", friendName=" + friendName + "]";
	}
	
	
}
