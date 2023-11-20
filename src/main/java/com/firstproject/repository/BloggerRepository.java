package com.firstproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.domain.Blogger;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, Long> {		// Repository gives us access to DB table
	
	List<Blogger> findAll();		// to match List<Story> in getStories() in HomeController...

}
