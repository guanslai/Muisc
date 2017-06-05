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

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * 
 * 专辑
 * 
 * */
@Entity
@Table(name="album")
public class Album {

	private Long id;
	private String albumName;//专辑名
	private String singer;//歌手
	private Date releaseDate;//专辑发布时间
	@JsonIgnore
	private Set<MusicInfo> musicsInAlbum = new HashSet<MusicInfo>();//专辑里的音乐
	
	public Album() {
	}
	
	public Album(Long id, String albumName, String singer, Date releaseDate, Set<MusicInfo> musicsInAlbum) {
		super();
		this.id = id;
		this.albumName = albumName;
		this.singer = singer;
		this.releaseDate = releaseDate;
		this.musicsInAlbum = musicsInAlbum;
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
	@Column(name="albumname")
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	@Column(name="singer")
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	@OneToMany(mappedBy="album")
	public Set<MusicInfo> getMusicsInAlbum() {
		return musicsInAlbum;
	}
	public void setMusicsInAlbum(Set<MusicInfo> musicsInAlbum) {
		this.musicsInAlbum = musicsInAlbum;
	}
	
	@Column(name="releasedate")
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", albumName=" + albumName + ", singer=" + singer + ", releaseDate=" + releaseDate
				+ ", musicsInAlbum=" + musicsInAlbum + "]";
	}

	
}
