package com.briup.bean;

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
 * 歌曲
 * 引入专辑：关系 ManyToOne
 * 
 * */
@Entity
@Table(name = "musicinfo")
public class MusicInfo {

	private Long id;
	private String musicName;// 歌曲名称
	private String path;// 歌曲存放路径
	private Integer historytimes;// 历史播放次数
	private Integer times;// 当前播放次数
	private Album album;// 专辑
	private String label;// 标签(原创，翻唱)
	private String type;// 类型(摇滚，电子，爵士，民谣)
	private String style;// 风格(忧伤，怀旧)
	private String area;// 区域(欧美，日语，韩语，粤语，话语)
	private Set<Relation> relation = new HashSet<Relation>();// 关系

	public MusicInfo() {
	}

	public MusicInfo(Long id, String musicName, String path, Integer historytimes, Integer times, Album album,
			String label, String type, String style, String area, Set<Relation> relation) {
		super();
		this.id = id;
		this.musicName = musicName;
		this.path = path;
		this.historytimes = historytimes;
		this.times = times;
		this.album = album;
		this.label = label;
		this.type = type;
		this.style = style;
		this.area = area;
		this.relation = relation;
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

	@Column(name = "musicname")
	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@OneToMany(mappedBy = "musics")
	public Set<Relation> getRelation() {
		return relation;
	}

	public void setRelation(Set<Relation> relation) {
		this.relation = relation;
	}

	@Column(name = "times")
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	@ManyToOne
	@JoinColumn(name = "albumid")
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Column(name = "label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "style")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name="historytimes")
	public Integer getHistorytimes() {
		return historytimes;
	}

	public void setHistorytimes(Integer historytimes) {
		this.historytimes = historytimes;
	}

	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MusicInfo [id=" + id + ", musicName=" + musicName + ", path=" + path + ", historytimes=" + historytimes
				+ ", times=" + times + ", album=" + album + ", label=" + label + ", type=" + type + ", style=" + style
				+ ", area=" + area + ", relation=" + relation + "]";
	}

	
}
