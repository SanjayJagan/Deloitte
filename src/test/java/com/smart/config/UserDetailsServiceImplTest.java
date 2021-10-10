package com.smart.config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {
	
	
	@InjectMocks
	private UserDetailsServiceImpl UserDetailsServiceImpl;
	@Mock
	private UserRepository userRepository;
	
	

	@Test
	public void loadUserByUserNameTest() {
		User user = new User();
		user.setName("Sanjay");
		user.setPassword("xyz");
		UserRepository mock = Mockito.mock(UserRepository.class);
		String username="Sanjay";
		when(userRepository.getUserByUserName(username)).thenReturn(user);
		UserDetailsServiceImpl.loadUserByUsername(username);
		assertNotNull(new CustomUserDetails(user));
	}
	
	
	@Test
	public void loadUserByUserNameTest1() {
		try {
			
		
	UserDetailsServiceImpl.loadUserByUsername("");
		
		}catch(Exception e) {
			
		}
	}
}
