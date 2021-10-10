package com.smart.controller;

import static org.junit.Assert.assertEquals;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.User;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest1 {

	

		
	
		
		@InjectMocks
		HomeController homeController;

		@Mock
		BCryptPasswordEncoder passwordEncoder;
		@Mock
		Model model;
		@Mock
		HttpSession session;
		@Mock
		BindingResult br;
		/*
		 * @Mock Principal principal;
		 */
		
		
		 @Mock 
		 private UserRepository userRepository;
		 
		
		@Test	
		public void homeTest()
			
			{
			
			String result=homeController.home(model);	
			assertEquals("home",result);
				
				
			}
		
		@Test	
		public void aboutTest()
			
			{
			
			String result=homeController.about(model);	
			assertEquals("about",result);
				
				
			}
		
		@Test	
		public void signUpTest()
			
			{
			
			String result=homeController.signup(model);	
			assertEquals("signup",result);
				
				
			}
		
		@Test	
		public void SigninTest()
			
			{
			
			String result=homeController.customLogin(model);	
			assertEquals("login",result);
				
				
			}
		@Test	
		public void aboutAppTest()
			
			{
			
			String result=homeController.aboutApp(model);	
			assertEquals("aboutApp",result);
				
				
			}
		@Test	
		public void registerUserTest()
			
			{
			User user=new User();
			user.setRole("www");
			user.setPassword("1www");
			boolean agreement = false;
			String result=homeController.registerUser(user,br,agreement,model,session);	
			assertEquals("signup",result);
				
				
			}
		
		@Test	
		public void registerUserTest1()
			
			{
			User user=new User();
			user.setRole("www");
			user.setPassword("1www");
			boolean agreement = true;
			String result=homeController.registerUser(user,br,agreement,model,session);	
			assertEquals("signup",result);
				
				
			}
		
}
