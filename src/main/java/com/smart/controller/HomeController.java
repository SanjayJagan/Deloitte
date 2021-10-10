package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model)
	
	{
		model.addAttribute("title","Home - TaskApp");
		return "home";
		
	}
	
	
	@RequestMapping("/about")
	public String about(Model model)
	
	{
		model.addAttribute("title","About - TaskApp");
		return "about";
		
	}
	@RequestMapping("/signup")
	public String signup(Model model)
	
	{
		model.addAttribute("title","SignUp - TaskApp");
		model.addAttribute("user",new User());
		
		
		return "signup";
		
	}
	
	//login
	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		model.addAttribute("title","Login Page");
		return "login";
	}
	
	
	//about application
	@GetMapping("/aboutApp")
	public String aboutApp(Model model)
	{
		model.addAttribute("title","About Application Page");
		return "aboutApp";
	}
	
	
	//register a user
	
	@RequestMapping(value="/do_register", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user")User user,BindingResult result1,
			@RequestParam(value="agreement",defaultValue="false")boolean agreement,
			Model model,HttpSession session)
	{
		try 
		{
			
			if(!agreement)
			{
				
				throw new Exception("please accept the agreement");
			}
			
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			/*
			 * if(result1.hasErrors()) { System.out.println("Error"+result1.toString());
			 * model.addAttribute("user",user); return "signup"; }
			 */
			
			System.out.println("Agreement"+agreement);
			System.out.println("USER"+user);
			
			User result= this.userRepository.save(user);
			
			model.addAttribute("user",result);
			session.setAttribute("message",new Message("Successfully Registered","alert-success"));
			return "signup";
			
		}catch(Exception e) {
			
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("something went wrong"+e.getMessage(),"alert-danger"));
			
		
		return "signup";
		}
	}
	
}
