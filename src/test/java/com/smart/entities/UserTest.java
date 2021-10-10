package com.smart.entities;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

	
	User user=new User();
	@BeforeEach
	public void init() {
	user.setPassword("www");
	user.setName("eee");
	user.setId(1);
	user.setRole("qqq");
	user.setContacts(null);
	}
	
	@Test
	public void getPasswordTest() {
		
		String result= user.getPassword();
		assertEquals("www",result);
	}

	@Test
	public void getUsernameTest() {
		
		String result1= user.getName();
		assertEquals("eee",result1);
	}

	@Test
	public void toStringTest() {
		
		String result2= user.toString();
		assertEquals(true,result2.contains("User"));
	}
	
	@Test
	public void getIdTest() {
		
		int result2= user.getId();
		assertEquals(1,result2);
	}
	
	@Test
	public void getRoleTest() {
		
		String result3= user.getRole();
		assertEquals("qqq",result3);
	}
	@Test
	public void getContactsTest() {
		
		List<Contact> result4= user.getContacts();
		assertEquals(null,result4);
	}
}
