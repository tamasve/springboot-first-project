package com.firstproject.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


// There can be a correct DB table definition, with email as a unique field
//
//@Table(
//        name = "blogger",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "blogger_email_unique", columnNames = "email")
//        }
@Entity
public class Blogger {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)		// DB should decide its value - further:
//	@SequenceGenerator( name..., sequenceName..., allocationSize = 1 ) - last one: incrementation
	@Column(name = "id", updatable = false)    // not to be changed
	@Id								// primary key in DB
	private long id;
	
	@Column(columnDefinition = "TEXT", nullable = false, unique = true)   // can not be null and should be unique
	private String name;
	
	private int age;
	
	@JsonBackReference					// to avoid infinite loop between Blogger and Story using a RestController (which returns a Story object)
	@OneToMany(mappedBy = "blogger")	// the other end of connection, here we have to give the mapper
	private List<Story> stories;
	
	
	public Blogger() {			// originally private! - @Query() wanted it to be public + serialization error suppress in app.prop
		
	}
	
	public Blogger(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

}
