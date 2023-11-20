package com.firstproject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.domain.Blogger;
import com.firstproject.domain.Story;
import com.firstproject.repository.BloggerRepository;
import com.firstproject.repository.StoryRepository;

import jakarta.annotation.PostConstruct;

@Service
public class StoryService {

	private StoryRepository storyRepo;
	private BloggerRepository bloggerRepo;

	@Autowired
	public void setStoryRepo(StoryRepository storyRepo) {
		this.storyRepo = storyRepo;
	}
	
	@Autowired
	public void setBloggerRepo(BloggerRepository bloggerRepo) {
		this.bloggerRepo = bloggerRepo;
	}
	
//	public Story getStory() {
//		return storyRepo.findFirstByOrderByPostedDesc();
//	}
	
	public List<Story> getStories() {
//		init();						  // bad place for it: it runs at every request - instead: spec. annotation for the method below
		return storyRepo.findAll();		// this will aut. use JDBC in the background (Select * from Story)	
	}

	public Story getSpecificStory(String title) {
		return storyRepo.findByTitle(title);			// it would be better handle the null case here
	}
	
	public Blogger getSpecificBlogger(Long id) {
		return bloggerRepo.findById(id).orElse(null);	// CrudRepository's findById returns Optional - it would be better handle the null case here
	}

//	public List<Story> getStoriesByBloggerName(String name) {
//		return storyRepo.findAllByBloggerNameIgnoreCaseOrderByPostedDesc(name);
//	}
	
	
	// How to create DB contend from the inside, here and not give it as a parameter in data.sql
	// create a blogger and his/her story and save it into DB through Repo
//	@PostConstruct			// = run 1x after this object was constructed
//	public void init() {
//		Blogger blogger = new Blogger("InnerGyula", 27);
//		bloggerRepo.save(blogger);							// save into DB through the object's own repo - without any SQL command!
//		Story story = new Story("Innertitle", "Innercontent", LocalDateTime.now(), blogger);
//		storyRepo.save(story);
//	}
}
