package com.briup.bean;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 
 * 歌单：每个用户有多个歌单
 * 引用歌曲：关系：OneToMany
 * 
 * */
@Entity
@Table(name="songsheet")
public class SongSheet {

	private Long id;
	private String sheetName;//歌单名称
	private MemberInfo memberinfo;//用户
	private Date createDate;//歌单创建时间
	private Set<Relation> relation = new HashSet<Relation>();//关系
	
	public SongSheet() {
	}
	
	public SongSheet(Long id, String sheetName, MemberInfo memberinfo, Date createDate, Set<Relation> relation) {
		super();
		this.id = id;
		this.sheetName = sheetName;
		this.memberinfo = memberinfo;
		this.createDate = createDate;
		this.relation = relation;
	}

	@Id
	@GeneratedValue(generator= "paymentableGenerator")
	@GenericGenerator(name= "paymentableGenerator",strategy = "native")
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="sheetname")
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	@ManyToOne
	@JoinColumn(name="memberid")
	public MemberInfo getMemberinfo() {
		return memberinfo;
	}
	
	public void setMemberinfo(MemberInfo memberinfo) {
		this.memberinfo = memberinfo;
	}
	
	@OneToMany(mappedBy="songSheet")
	public Set<Relation> getRelation() {
		return relation;
	}

	public void setRelation(Set<Relation> relation) {
		this.relation = relation;
	}

	@Column(name="createdate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "SongSheet [id=" + id + ", name=" + sheetName + ", memberInfo=" + memberinfo + "]";
	}
}
