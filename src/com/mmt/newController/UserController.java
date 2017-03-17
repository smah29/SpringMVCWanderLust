package com.mmt.newController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.mmt.model.bean.Flight;
import com.mmt.model.bean.Promotion;
import com.mmt.model.bean.User;
import com.mmt.model.bl.FlightBookingBlMMT;
import com.mmt.model.bl.HotelBlMMT;
import com.mmt.model.bl.PromotionBlMMT;
import com.mmt.model.bl.UserBlMMT;


@SessionAttributes({"user","flight"})
@Controller
public class UserController {
	private FlightBookingBlMMT flightBookingBlMMT = new FlightBookingBlMMT();
	private HotelBlMMT hotelBlMMT = new HotelBlMMT();
	private UserBlMMT userBl=new UserBlMMT();
	PromotionBlMMT promoBl = new PromotionBlMMT();
	ArrayList<Promotion> arrayListPromoFlight = null;
	@RequestMapping("/")
	public String newRegistration1(){
		
		return "BlackHeader";
	}
	@RequestMapping("/login")
	public String newRegistration2(){
	
		return "Login";
	}
	@RequestMapping("/signup")
	public ModelAndView newRegistration(){
	
		return new ModelAndView("signup", "user", new User());
	}
	
	@RequestMapping("/userRegister")
	public String userRegisterStatus(@Valid User user, BindingResult result,ModelMap model){
		
		String view=null;
		if(result.hasErrors()){
			view="signup";
			
		}
		else{
		try {
			if(userBl.register(user)){
				//model.addAttribute("message", user.getUserName() +  " Successfully registered");
				view="Login";

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
	@RequestMapping("/loginCheck")
	public String userLoginCheck(HttpServletRequest request,ModelMap model){
		String username=request.getParameter("username");
        String password=request.getParameter("password");
        
		String view=null;
		User user=null;
		
		try {
			
			user =(User)userBl.checkLogin(username, password);
			model.addAttribute("user", user);
			if(user != null){
				
				view="UserDashBoard";

			}
			else{
				model.addAttribute("message","Login Failed");
				view="status";
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
		
			e.printStackTrace();
		}
		
		return view;
		
		
	}
	@RequestMapping("/searchFlight")
	public String searchFlight(ModelMap model) {
		Flight flight=new Flight();
		model.addAttribute("flight", flight);
		return "FlightForm";
	}
	@RequestMapping("/SelectFlight")
	public ModelAndView SelectFlight(@ModelAttribute("flight") Flight flight) {
		List<Flight> flightList=new ArrayList<Flight>();
		try {
			flightList=flightBookingBlMMT.searchFlight(flight.getFlightSource(), flight.getFlightDestination());
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView("LoggedInDisplayAllFlightsStD", "arrayListFlight", flightList);
		return mv;
	}
	@RequestMapping("/ChoosePromoFlight")
	public String chhoosePromoFlight(HttpSession session,ModelMap model){
		try {
			arrayListPromoFlight = promoBl.displayPromotion("FLIGHT");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("arrayListPromoFlight", arrayListPromoFlight);
		return "ChoosePromoCodeFlight";
		
	}
	@RequestMapping("/Payment")
	@ModelAttribute("flightSourceList")
	public Set<String> flightSourceList() {

		Set<String> flightSourceList = new HashSet<String>();
		try {

			flightSourceList = flightBookingBlMMT.getSourceList();

		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return flightSourceList;
	}

	@ModelAttribute("flightDestList")
	public Set<String> flightDestList() {

		Set<String> flightDestList = new HashSet<String>();
		try {

			flightDestList =  flightBookingBlMMT.getDestinationList();

		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return flightDestList;
	}
	
	
	@ModelAttribute("hotelList")
	public Set<String> hotelList() {

		Set<String> hotelList = new HashSet<String>();
		try {

			hotelList =  hotelBlMMT.getHotelLocationList();

		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return hotelList;
	}
	
}
