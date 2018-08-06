package com.hcl.symantec.rnd.basic.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * This the end point which is used to map the user request to fetch or updated the books in a book repository.
 * @author naveen_b
 *
 */
@RestController
public class BooksController {
	
	@Autowired
	BookService bookservice;
	
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		
		return bookservice.getAllBooks();		
	}
}