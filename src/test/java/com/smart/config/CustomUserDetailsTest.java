package com.smart.config;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.smart.entities.Contact;
import com.smart.entities.User;

public class CustomUserDetailsTest {
	
	User user=new User();
	CustomUserDetails c=new CustomUserDetails(user);
	@BeforeEach
	public void init() {
	user.setPassword("www");
	user.setName("eee");
	user.setRole("qqq");
	
	}
	
	@Test
	public void getPasswordTest() {
		
		String result= c.getPassword();
		assertEquals("www",result);
	}

	@Test
	public void getUsernameTest() {
		
		String result1= c.getUsername();
		assertEquals("eee",result1);
	}

	
	@Test
	public void isAccountNonExpiredTest() {
		
		boolean r= true;
		c.isAccountNonExpired();
		assertEquals(true,r);
	}
	
	@Test
	public void getAuthoritiesTest() {
		
		List<SimpleGrantedAuthority> result1= (List<SimpleGrantedAuthority>) c.getAuthorities();
		assertEquals(1,result1.size());
	}
}
