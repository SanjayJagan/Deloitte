package com.smart.helper;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MessageTest {
	
	
	Message con= new Message();
	@BeforeEach
	public void init() {
		con.setContent("www");
		con.setType("aaa");
		
	}
	
	@Test
	public void MessageTest1() {
		Message result= new Message("a","aa");
				
	}
	@Test
	public void getContentTest() {
		
		String result= con.getContent();
		assertEquals("www",result);
	}
	@Test
	public void getTypeTest() {
		
		String result= con.getType();
		assertEquals("aaa",result);
	}
	
	
	
}
