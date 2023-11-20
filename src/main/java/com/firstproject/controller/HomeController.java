package com.firstproject.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firstproject.service.StoryService;

import jakarta.servlet.http.HttpServletRequest;


// -- LAYERS --
// View  ->  Controller   ->   Service            -> Repository       -> (Entities) Database
// UI       direct, manage   calculate, bus.logic   communicate with DB


@Controller						// return values in methods here = html page names (under 'resources')
public class HomeController {
	
	private StoryService storyService;
	
	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}
	


	@RequestMapping("/")
	public String stories(Model model, Locale locale) {				// ThymeLeaf: Model will transport the data (as attributes) between HTML/CSS front-end and Java back-end
		
		model.addAttribute("pageTitle", "SFJ story for every day!");	// 1st param = ${id} given in HTML (th:text="${pageTitle}), 2nd param = value
		model.addAttribute("stories", storyService.getStories());		// 2nd param here = an object: a List of stories...
		
		System.out.println(String.format("Get all stories request received, language: %s, country: %s %n", locale.getLanguage(), locale.getDisplayCountry()));
		
		return "basic";		// the name of the View - it will show as "basic.html" (it will search for such a file...)  +  all data transport into HTML: "th:text=..."
	}
	
	// these will be handled in a RestController because now we do not deal with front-end
	
//	@RequestMapping("/story")
//	public String story(Model model) {				// ThymeLeaf: Model will transport the data between HTML front-end and Java back-end
//		
//		model.addAttribute("pageTitle", "Only 1 SFJ story for today...");	// 1st param = ${id} given in HTML (th:text="${pageTitle}), 2nd param = value
//		
//		model.addAttribute("story", storyService.getStory());		// 2nd param here = an object...
//		
//		System.out.println("Get 1 story request received - story = " + storyService.getStory());
//		
//		return "basic-1story";
//	}
//	
	
	@RequestMapping("/title/{title}")
	public String searchForStory(@PathVariable("title") String title, Model model) throws Exception {		// how to get part of the mapping as a parameter, dynamic variable from a link
		if (title == null)
			throw new Exception("No story with this title");										// exception will be directed to the below ExceptionHandler method
		model.addAttribute("story", storyService.getSpecificStory(title));
		return "basic-1story";
//		return storyService.getSpecificStory(title);			// this would be if this class were a RestController (which returns an object)
	}
	
	
	// unfinished...
	@RequestMapping("/user/{id}")
	public String searchForUser(@PathVariable("id") String id, Model model) throws Exception {		// how to get part of the mapping as a parameter, dynamic variable from a link
		if (id == null)
			throw new Exception("No such ID");										// exception will be directed to the below ExceptionHandler method
		model.addAttribute("blogger", storyService.getSpecificBlogger(Long.valueOf(id)));
		return "user";					// go on to handle data (in user.html)
	}
	
	
	@ExceptionHandler(Exception.class)													// the other way: common exc handler class (here ExceptionGeneral)
	public String exceptionHandler(HttpServletRequest request, Exception ex, Model model) {
		System.out.println("exceptionHandler runs...");
		model.addAttribute("pageTitle", "Sorry, there was an error...");
		model.addAttribute("error", ex.getLocalizedMessage());
		model.addAttribute("message", ex.getMessage());		// put exc message into model then call the relevant html
		return "error";
	}
	
	
	// HOW TO INSERT DATA INTO DB AT START
	
// 3rd version: this method (2nd version) is in a separate StoryService class
	
// 2nd version for sending stories to the front-end: querying them from DB
	
//	private List<Story> getStories() {
//		
//		List<Story> stories = storyRepo.findAll();		// this will aut. use JDBC in the background (Select * from Story)	
//		return stories;
//	}
	
	
// 1st version: in-place created stories		
//	private ArrayList<Story> getStories() {		
//	
//		ArrayList<Story> stories = new ArrayList<>();
//
//		Story story1 = new Story();
//		story1.setTitle("My first story");
//		story1.setPosted(LocalDateTime.now());
//		story1.setAuthor("Thomas");
//		story1.setContent("<p>This data is prod data...</p>");
//		
//		Story story2 = new Story();
//		story2.setTitle("Gyula's story");
//		story2.setPosted(LocalDateTime.now().minusMinutes(50));
//		story2.setAuthor("Gyula");
//		story2.setContent("<p>Nobody wants to hear Gyula's story...</p>");
//		
//		stories.add(story1);
//		stories.add(story2);
//		
//		return stories;
//	}

}
