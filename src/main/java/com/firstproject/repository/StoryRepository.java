package com.firstproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.firstproject.domain.Story;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {		// Repository gives us access to DB table - JPA can help us here
	
	// SELECT * FROM STORIES
	List<Story> findAll();		// not to be Iterable<>, to match List<Story> in getStories() in HomeController...

	// SELECT * FROM STORIES WHERE posted IN (SELECT max(posted) FROM STORIES) LIMIT 1
	Story findFirstByOrderByPostedDesc();		// the method name defines what SQL command should be generated in the background by JPA

	
	List<Story> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);		// JPA will recognize it and will be able to create the SQL! - it can even be case insensitive :)
	
	
	// what if WE WANT TO WRITE SQL query?	-  it works only with a serialization fail suppress in app.prop
	
//	@Query(value = "SELECT * FROM stories WHERE title = ?1 limit 1", nativeQuery=true)		// ordinal parameter... ("sql...") -> JPQL, which gives back result as an object, 2nd parameter will change it to be normal
//	Story findByTitle(String title);
	
	// 2nd version:
	@Query(value = "SELECT * FROM stories WHERE title = :title limit 1", nativeQuery=true)		// we use named parameter...
//	@Query(value = "SELECT s FROM Story s WHERE s.title = :title")		// JPQL version - here Story stands because we need the object itself not the table from DB! - but it does not work... can not validate query
	Story findByTitle(@Param("title") String title);
}
