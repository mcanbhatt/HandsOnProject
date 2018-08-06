package com.hcl.symantec.rnd.basic.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is Service that performs the business logic and delegates to fetch data from DAO layer.
 * 
 * @author naveen_b
 *
 */
@Service
public class BookService {
	
	@Autowired
	BookDAO bookDAO;
	
	/**
	 * This method delegate the it call to DAO layer to fetch information of the book
	 * 
	 * @return List of books
	 */
	
	public List<Book> getAllBooks(){
		return bookDAO.loadbooks();
		
		
	}

}
