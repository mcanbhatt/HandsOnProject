package com.hcl.symantec.rnd.basic.springboot;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value =BooksController.class)
public class SpringbootBasicApplicationTests {

	/**
	 * Autowire's the Mocking frame work to perform the task.
	 */
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * Injects the service class and mock the data on it.
	 */
	@MockBean
	private BookService bookService;
	
	/**
	 *  This test case validates that the the code#getAllBooks method retrun's the correct list of books that are mocked 
	 *  and the result is return in the accepted format.
	 *   
	 * @throws Exception  when an error occurred while fetching data from the URI.
	 */
	
	@Test
	public void getAllBooks() throws Exception {
		
		List<Book> mockBookList = new ArrayList<>();
				mockBookList.add(new Book("l1", "Mastering Spring", "Naveen Bhatt"));
	 
		Mockito.when(bookService.getAllBooks()).thenReturn(mockBookList);
		
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON);
		MvcResult result =  mockMvc.perform(reqBuilder).andReturn();
		
		String expected = "["+"{\"name\":\"Mastering Spring\"}"+"]";
		
		System.out.println(result.getResponse().getContentAsString());
		//{"id":1,"name":"Mastering Spring","author":"Naveen Bhatt"}
		System.out.println(expected);
		
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(), false);  		
	}

}
