package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.User;
import com.app.services.UserService;

@Controller
public class ApplicationController {
	
    @Autowired
	private UserService service;
    
	/*
	 * @Autowired private UserRepository repo;
	 */
	
	@RequestMapping("/home")
	public String welcome(HttpServletRequest request)
	{
		request.setAttribute("mode","MODE_HOME");
		return "homePage";
	}
	
	@RequestMapping("/register")
	public String registration(HttpServletRequest request)
	{
		request.setAttribute("mode","MODE_REGISTER");
		return"homePage";
		
	}
	
	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user,BindingResult bindingResult,HttpServletRequest request)
	{
		service.saveMyuser(user);
		 request.setAttribute("mode","MODE_HOME");
		return "homePage";
	}
	
	@GetMapping("/show-users")
	public String ShowAllUser(HttpServletRequest request,Model model)
	{ 
		int currentpage=1;
		Page<User> page=service.pageList();
		long totalitem=page.getTotalElements();
		int totalpage=page.getTotalPages();
		
		List<User>pagelist=page.getContent();
		
	    //request.setAttribute("users",service.showAllUsers());
		request.setAttribute("mode","ALL_USERS");
		model.addAttribute("currentpage",currentpage);
		model.addAttribute("totalitem",totalitem);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("users",pagelist);
		
		return "homePage";
	}
	
	/*@GetMapping("/page/{pageNumber}")
	public String listBypage(Model model ,
			
			@PathVariable("pageNumber")int currentpage){
			
		return "";
	}*/
	
  @RequestMapping("/delete-user")	
  public String deleteUser(@RequestParam int id, HttpServletRequest request)
  {
	service.deleteMyUser(id);
	request.setAttribute("users", service.showAllUsers());
	request.setAttribute("mode", "ALL_USERS");
	return "homePage";
	  
  }
  
  @RequestMapping("/edit-user")
  public String editUser(@RequestParam int id, HttpServletRequest request)
  { 
	  request.setAttribute("user",service.editUser(id));
	  request.setAttribute("mode", "MODE_UPDATE");
	  return "homePage";
  }
  
  @RequestMapping("/login")
  public String login(HttpServletRequest request)
  { 
	  request.setAttribute("mode","MODE_LOGIN");
	 return "homePage";
  }
  
  @RequestMapping("/login-user")
  public String loingUser(@ModelAttribute User user,HttpServletRequest request)
  { 
	  if(service.findByUsernameAndPassword(user.getUsername(),user.getPassword())!=null) {	
		
		  return "welcome";
		 
		}
	  
	  else {
		    request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "homePage";
	  }
	  
	 
  }
	
}
