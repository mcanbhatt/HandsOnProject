package com.hcl.symantec.rnd.basic.springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;


/*
 *  This is DAO class which interact with the service layer and  database. Fetch/store the data 
 *  as per the business logic.
 * @author naveen_b
 *
 */
@Repository
public class BookDAO {
	
	/**
	 * Load book information from the back end
	 * 
	 * @return list of books
	 */
	public List<Book> loadbooks(){
		
		return Arrays.asList(
				new Book("l1", "Mastering Spring", "Naveen Bhatt"), new Book("l1", "Mastering Spring", "Vinod Pujara"));
	}
	
	/*private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session sess() {
        return sessionFactory.getCurrentSession();
    }*/

}
