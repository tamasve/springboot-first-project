package com.firstproject.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "stories")				// if we want a different table name in DB - the default equals to the classname 
public class Story {	// for JPA - private constructor + regular setters and getters are necessary!
														
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// DB should decide its value
	@Id								// primary key in DB
	private long id;
	
	private String title;
	
//	@Column(length = 1000)			// different length from the default (255)
	@Column(columnDefinition = "TEXT")		// huge text field instead of varchar
	private String content;
	
//	@Column(name = "posttime")		// different field name in DB
	private LocalDateTime posted;
	
	@ManyToOne						// Many = this (Story), One = the other (Blogger)
	private Blogger blogger;
	
	
	public Story() {
		
	}
	
	public Story(String title, String content, LocalDateTime posted, Blogger blogger) {
		this.title = title;
		this.content = content;
		this.posted = posted;
		this.blogger = blogger;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getPosted() {
		return posted;
	}
	public void setPosted(LocalDateTime posted) {
		this.posted = posted;
	}
	public Blogger getBlogger() {
		return blogger;
	}
	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

	public String getPostedAsString() {
		return getPosted().format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") );
	}
	
	@Override
	public String toString() {
		return "Story [title=" + title + " content=" + content + "]";
	}
}
