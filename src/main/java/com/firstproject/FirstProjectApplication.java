package com.firstproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.firstproject.domain.Blogger;
import com.firstproject.repository.BloggerRepository;


/**
 * First Spring Boot practice project during SFJ tutorials
 * 
 * February 2023
 */


@SpringBootApplication
public class FirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectApplication.class, args);		// This method has not a void return value, it returns ApplicationContext ('Mr. Container' - the note-book of all info about this app)
	}

	// this object is created (and 'runs') after the above static part
	@Bean
	CommandLineRunner commandLineRunner(BloggerRepository bloggerRepository) {		// this puts a 5th blogger into Blogger table (4 from data.sql which runs before it)
		return args -> {
			Blogger jones = new Blogger("Jones", 34);
			bloggerRepository.save(jones);
		};
	}
}
