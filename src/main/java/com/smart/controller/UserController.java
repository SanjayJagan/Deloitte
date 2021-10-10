package com.smart.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME" + userName);
		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER" + user);

		model.addAttribute("user", user);
		// return"";
	}

	/*
	 * //home dashboard
	 */ 
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title", "User Dashboard");
		String userName=principal.getName();
		System.out.println(userName);
		
		User user=userRepository.getUserByUserName(userName);
		System.out.println("USER"+user);
		model.addAttribute("user",user);
		
		return "normal/user_dashboard";
	}
	 
	// update task handler
		
		@PostMapping("/update-contact/{cid}")
		public String updateForm(@PathVariable("cid") Integer cid,Model m)
		{
			
			m.addAttribute("title","Update task");
			Contact contact=this.contactRepository.findById(cid).get();
			
			m.addAttribute("contact",contact);
			return "normal/update_form";
			
		}
		

	/*
	 * //open add form handler
	 */ @GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "Add Tasks");
		model.addAttribute("content", new Contact());
		return "normal/add_contact_form";
	}

	// processing add tasks
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact content, Principal principal, HttpSession session) {

		String name = principal.getName();
		User user = this.userRepository.getUserByUserName(name);
		content.setUser(user);
		user.getContacts().add(content);
		this.userRepository.save(user);

		System.out.println("DATA" + content);
		System.out.println("added to database");
		//session.setAttribute("message", new Message("Your Task added successfully...You can add another if you wish", "success"));

		return "normal/add_contact_form";
	}
	
	

	// View tasks handler
	
	// pagination 5 tasks per page
	
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page, Model m, Principal principal) {

		
		// send list of tasks for that user

		 
		m.addAttribute("title", "View User Tasks");
		String userName= principal.getName();
		 
		 User user =this.userRepository.getUserByUserName(userName); 
		 
		Pageable pageable= PageRequest.of(page,3);
		 Page<Contact> tasks=this.contactRepository.findTaskByUser(user.getId(),pageable);
		 m.addAttribute("tasks",tasks);
		 m.addAttribute("currentPage",page);
		 m.addAttribute("totalPages",tasks.getTotalPages());
		 
		 
		return "normal/show_contacts";

	}
	
	//show particular task
	@RequestMapping("/{cId}/contact")
	public String showTaskDetail(@PathVariable("cId") Integer cId, Model m, Principal principal)
	{
		Optional<Contact> contactOptional = this.contactRepository.findById(cId);
		Contact contact=contactOptional.get();
		
		String username=principal.getName();
		User user=this.userRepository.getUserByUserName(username);
		
		if(user.getId()==contact.getUser().getId())
		{
			m.addAttribute("contact",contact);
		}
		
		return "normal/task_detail";
	}

	
	//delete tasks handler
	
	@GetMapping("/delete/{cid}")
	public String deleteTask(@PathVariable("cid") Integer cId, Model m, HttpSession session,Principal principal)
	{
		Optional<Contact> contactOptional=this.contactRepository.findById(cId);
		
		Contact contact=contactOptional.get();
		
		//contact.setUser(null);
		//this.contactRepository.delete(contact);
		User user=this.userRepository.getUserByUserName(principal.getName());
		user.getContacts().remove(contact);
		
		this.userRepository.save(user);
		//session.setAttribute("message",new Message("task deleted successfully","success"));
		return "redirect:/user/show-contacts/0";
	}


	
	// update task handler
	
	@RequestMapping(value = "/process-update",method=RequestMethod.POST)
	public String updateHandler(@ModelAttribute Contact contact, Model m,Principal principal)
	{
		try {
			
			User user=this.userRepository.getUserByUserName(principal.getName());
			
			contact.setUser(user);
			this.contactRepository.save(contact);
			
		}catch(Exception e) {
			//e.printStackTrace();
		}
		
		
		return "redirect:/user/show-contacts/0";
	//	return "redirect:/user/"+contact.getcId()+"/contact";
	}

}
