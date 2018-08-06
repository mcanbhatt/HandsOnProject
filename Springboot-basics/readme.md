##  First Basic of Spring Boot

- Introduction to Spring Boot - Goals and Important Features
- Developing Spring Applications before Spring Boot
- Using Spring Initializr to create a Spring Boot Application
- Creating a Simple REST Controller
-  What is Spring Boot Auto Configuration?
- Spring Boot Starter Projects - Starter Web and Starter JPA
- Spring Boot Actuator
- Spring Boot Developer Tools


## Complete Code Example

### /notes.txt

```
Goals
Enable building production ready applications quickly
Provide common non-functional features 
- embedded servers
- metrics
- health checks
- externalized configuration

What Spring Boot is NOT!
ZERO code generation
Neither an application server nor a web server

Features
Quick Starter Projects with Auto Configuration
 - Web
 - JPA
Embedded Servers - Tomcat, Jetty or Undertow
Production-ready features
 - metrics and health checks 
 - externalized configuration
 
 
http://localhost:8080/books => Few hardcoded books
 
```
---

### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.hcl.symantec.rnd.springboot.basics</groupId>
	<artifactId>springboot-basics</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>springboot-boot-learning</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.0.BUILD-SNAPSHOT</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-rest-hal-browser</artifactId>
		</dependency>

<!-- 
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		 -->
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

	<repositories>
		<repository>
			<id>spring-snapshots</id>
			<name>Spring Snapshots</name>
			<url>https://repo.spring.io/snapshot</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>

	<pluginRepositories>
		<pluginRepository>
			<id>spring-snapshots</id>
			<name>Spring Snapshots</name>
			<url>https://repo.spring.io/snapshot</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</pluginRepository>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>


</project>
```
---

### /src/main/java/com/hcl/symantec/rnd/basic/springboot/Book.java

```java
package com.hcl.symantec.rnd.basic.springboot;

public class Book {
	String id;
	String name;
	String author;

	public Book(String id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return String.format("Book [id=%s, name=%s, author=%s]", id, name, author);
	}

}
```
---

### /src/main/java/com/hcl/symantec/rnd/basic/springboot/BooksController.java

```java
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
```
---

### /src/main/java/com/hcl/symantec/rnd/basic/springboot/BookService.java

```java
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

```
---

### /src/main/java/com/hcl/symantec/rnd/basic/springboot/BookDAO.java

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


```
---

### /src/main/java/com/hcl/symantec/rnd/basic/springboot/SpringbootBasicApplication.java

```java
package com.hcl.symantec.rnd.basic.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootBasicApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = 
				SpringApplication.run(SpringbootBasicApplication.class, args);
		
		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}

```
---

### /src/main/resources/application.properties

```properties
#logging.level.org.springframework = DEBUG
management.security.enabled=false
```
---

### /src/test/java/com/hcl/symantec/rnd/basic/springboot/SpringbootBasicApplicationTests.java

```java
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


```
---
