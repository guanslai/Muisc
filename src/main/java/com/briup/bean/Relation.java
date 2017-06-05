package com.briup.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 
 * 关系表：
 * 		和歌单的关系：OneToMany
 * 		和音乐的关系：OneToMany
 * 
 * */
@Entity
@Table(name="relation")
public class Relation {
	
	private Long id;
	private MusicInfo musics;
	private SongSheet songSheet;
	
	public Relation() {
	}

	public Relation(Long id, MusicInfo musics, SongSheet songSheet) {
		super();
		this.id = id;
		this.musics = musics;
		this.songSheet = songSheet;
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

	@ManyToOne
	@JoinColumn(name="music_id")
	public MusicInfo getMusics() {
		return musics;
	}

	public void setMusics(MusicInfo musics) {
		this.musics = musics;
	}

	@ManyToOne
	@JoinColumn(name="sheet_id")
	public SongSheet getSongSheet() {
		return songSheet;
	}

	public void setSongSheet(SongSheet songSheet) {
		this.songSheet = songSheet;
	}

	@Override
	public String toString() {
		return "Relation [id=" + id + ", musics=" + musics + ", songSheet=" + songSheet + "]";
	}
}
