package com.mmt.newController;

import java.io.IOException;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import org.springframework.web.servlet.ModelAndView;
import com.mmt.model.bean.User;
import com.mmt.model.bl.UserBlMMT;



@Controller
public class UserController {
	
	private UserBlMMT userBl=new UserBlMMT();
	@RequestMapping("/")
	public String newRegistration1(){
		
		return "BlackHeader";
	}
	@RequestMapping("/signup")
	public ModelAndView newRegistration(){
	
		return new ModelAndView("signup", "user", new User());
	}
	@RequestMapping("/userRegister")
	public String userRegisterStatus(@Valid User user, BindingResult result,ModelMap model){
		//ModelAndView modelAndView=new ModelAndView();
		String view=null;
		if(result.hasErrors()){
			view="signup";
			
		}
		else{
		try {
			if(userBl.register(user)){
				//model.addAttribute("message", user.getUserName() +  " Successfully registered");
				view="login";

			}
			else{
				model.addAttribute("message", user.getUserName() +  "Registration Failed");
				view="status";
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
		
			e.printStackTrace();
		}
		}
		return view;
		
		
	}
	
}
