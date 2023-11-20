package com.firstproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.domain.Story;
import com.firstproject.service.StoryService;

@RestController
public class ApiController {
	
	private StoryService storyService;
	
	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}
	
	
//	@RequestMapping("/story")
//	public Story story() {				// ThymeLeaf and Model are no more because we deal with only the back-end...
//		System.out.println("Get 1 story request received - story = " + storyService.getStory());
//		return storyService.getStory();			// this will write Story object in JSON form
//	}
//	
//	
//	@RequestMapping("/title/{title}")
//	public Story searchForStory(@PathVariable("title") String title) throws Exception {		// how to get part of the mapping as a parameter, dynamic variable from a link
//		if (title == null)
//			throw new Exception("No story with this title: " + title);										// exception will be directed to the below ExceptionHandler method
//		return storyService.getSpecificStory(title);
//	}
//	
//	@RequestMapping("/stories/{name}")
//	public List<Story> searchForStoriesByBloggerName(@PathVariable("name") String name) throws Exception {		// how to get part of the mapping as a parameter, dynamic variable from a link
//		if (name == null)
//			throw new Exception("No stories found from this person: " + name);										// exception will be directed to the below ExceptionHandler method
//		return storyService.getStoriesByBloggerName(name);
//	}

}
