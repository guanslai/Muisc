package com.briup.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

/*
 * 
 * 会员信息
 * 引用歌单id：和歌单关系OneToMany
 * 
 * 
 * */
@Component
@Entity
@Table(name = "memberinfo")
public class MemberInfo {

	private Long id;
	private String username;// 用户名
	private String password;// 密码
	private String email;// 邮箱
	private Date registerdate;// 注册时间
	private Set<SongSheet> songSheet = new HashSet<SongSheet>();// 歌单

	public MemberInfo() {
	}

	public MemberInfo(Long id, String username, String password, String email, Date registerdate,
			Set<SongSheet> songSheet) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.registerdate = registerdate;
		this.songSheet = songSheet;
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

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy="memberinfo")
	public Set<SongSheet> getSongSheet() {
		return songSheet;
	}

	public void setSongSheet(Set<SongSheet> songSheet) {
		this.songSheet = songSheet;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="registerdate")
	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	@Override
	public String toString() {
		return "MemberInfo [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", registerdate=" + registerdate + ", songSheet=" + songSheet + "]";
	}
	
}
