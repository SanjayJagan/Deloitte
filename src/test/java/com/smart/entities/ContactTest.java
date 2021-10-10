package com.smart.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ContactTest {

	Contact contact=new Contact();
	@BeforeEach
	public void init() {
	contact.setcId(1);
	contact.setContent("aaa");
	contact.setTitle("sss");
	contact.setUser(null);
	}
		@Test
	public void getcIdTest() {
		
		int result= contact.getcId();
		assertEquals(1,result);
	}
		
	/*
	 * @Test public void setcIdTest() {
	 * 
	 * int result= contact.getcId(); assertEquals(1,result);
	 * 
	 * }
	 */
	@Test
	public void getTitleTest() {
		
		
		String result= contact.getTitle();
		assertEquals("sss",result) ;
	}
	
	/*
	 * @Test public void setTitleTest() {
	 * 
	 * 
	 * int result= contact.getcId(); assertEquals(1,result); }
	 */
	
	@Test
	public void getContentTest() {
		
		
		String result= contact.getContent();
		assertEquals("aaa",result) ;
	}
	
	/*
	 * @Test public void setContentTest() {
	 * 
	 * 
	 * int result= contact.getcId(); assertEquals(1,result) ; }
	 */
	
	@Test
	public void getUserTest() {
		
		
		User result= contact.getUser();
		assertEquals(null,result) ;
	}
	
	@Test
	public void EqualsTest() {
		
		
		//User result= contact.getUser();
		assertEquals(true,contact.equals(contact)) ;
		assertEquals(false,contact.equals(new Contact())) ;
	}
	
	/*
	 * @Test public void setUserTest() {
	 * 
	 * 
	 * contact.setUser(null); assertEquals(null,contact.getUser()) ; }
	 */
	
	
}
