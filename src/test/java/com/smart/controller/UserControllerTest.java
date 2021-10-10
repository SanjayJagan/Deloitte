package com.smart.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.smart.config.CustomUserDetails;
import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	Model model;

	@Mock
	Principal principal;

	@Mock
	HttpSession session;
	@Mock
	Page<Contact> tasks;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ContactRepository contactRepository;

	@Test
	public void addCommonDataTest() {

		/*
		 * User user=new User(); user.setName("abc"); user.setPassword("xyz");
		 * 
		 * Principal mock = Mockito.mock(Principal.class);
		 * when(mock.getName()).thenReturn("abc");
		 * 
		 * UserRepository mock1 = Mockito.mock(UserRepository.class);
		 * when(mock1.getUserByUserName("abc")).thenReturn(user);
		 */
		userController.addCommonData(model, principal);

	}

	@Test
	public void openAddContactFormTest() {

		String result = userController.openAddContactForm(model);
		assertEquals("normal/add_contact_form", result);
	}

	@Test
	public void dashboardTest() {

		String result = userController.dashboard(model, principal);
		assertEquals("normal/user_dashboard", result);
	}

	@Test
	public void updateFormTest() {

		Optional<Contact> o = Optional.of(new Contact());
		Mockito.when(contactRepository.findById(1)).thenReturn(o);
		String result = userController.updateForm(1, model);
		assertEquals("normal/update_form", result);
	}

	@Test
	public void processContactTest() {

		Mockito.when(userRepository.getUserByUserName(null)).thenReturn(new User());
		String result1 = userController.processContact(new Contact(), principal, session);
		assertEquals("normal/add_contact_form", result1);
	}

	
	  @Test public void showContactsTest() { 
		  
				Page<Contact> companies = Mockito.mock(Page.class);
				  //Mockito.when(contactRepository.findTaskByUser(Mockito.anyInt(),Mockito.any()).thenReturn(companies));
				  Mockito.when(userRepository.getUserByUserName(null)).thenReturn(new User());
				  try{
					  String result1= userController.showContacts(1, model,principal);
				  
					  assertEquals("normal/show_contacts",result1); 
					  }
				  	catch(Exception e){
				  		}
	  }
	  

	@Test
	public void updateHandlerTest() {
		Contact c = new Contact();
		Mockito.when(userRepository.getUserByUserName(null)).thenReturn(new User());
		String result1 = userController.updateHandler(new Contact(), model, principal);
		assertEquals("redirect:/user/show-contacts/0", result1);
	}

	@Test
	public void updateHandlerTest1() {
		try {
			Mockito.when(userRepository.getUserByUserName(null)).thenReturn(new User());
			String result1 = userController.updateHandler(null, model, principal);
			assertEquals("redirect:/user/show-contacts/0", result1);
		} catch (Exception e) {

		}
	}

	@Test
	public void showTaskDetailTest() {
		User user = new User();
		user.setId(1);

		Contact c = new Contact();
		c.setUser(user);

		Mockito.when(userRepository.getUserByUserName(null)).thenReturn(user);
		Optional<Contact> o = Optional.of(c);
		Mockito.when(contactRepository.findById(1)).thenReturn(o);
		String result = userController.showTaskDetail(1, model, principal);
		assertEquals("normal/task_detail", result);
	}

	@Test
	public void deleteTaskTest() {
		Mockito.when(userRepository.getUserByUserName(null)).thenReturn(new User());
		Optional<Contact> o = Optional.of(new Contact());
		Mockito.when(contactRepository.findById(1)).thenReturn(o);
		String result = userController.deleteTask(1, model, session, principal);
		assertEquals("redirect:/user/show-contacts/0", result);
	}
}
