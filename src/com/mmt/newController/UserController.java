package com.mmt.newController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.model.bean.Admin;
import com.mmt.model.bean.Flight;
import com.mmt.model.bean.FlightBooking;
import com.mmt.model.bean.Promotion;
import com.mmt.model.bean.User;
import com.mmt.model.bl.AdminBlMMT;
import com.mmt.model.bl.FlightBookingBlMMT;
import com.mmt.model.bl.FlightPaymentBl;
import com.mmt.model.bl.HotelBlMMT;
import com.mmt.model.bl.PromotionBlMMT;
import com.mmt.model.bl.UserBlMMT;
import com.mmt.model.bl.WalletBlMMT;



@SessionAttributes({"user","admin","flightSource","flightDestination","departureDate","flight","moneyToBeAdded","messageFlight","noOfSeats","balance","msgMoney","finalValuetobepaid","flightBooking","nomessage"})
@Controller
public class UserController {
	private FlightBookingBlMMT flightBookingBlMMT = new FlightBookingBlMMT();
	FlightBooking flightBooking = new FlightBooking();
	private AdminBlMMT adminBlMMT = new AdminBlMMT();
	private UserBlMMT userBl=new UserBlMMT();
	PromotionBlMMT promoBl = new PromotionBlMMT();
	ArrayList<Promotion> arrayListPromoFlight = null;
	FlightPaymentBl flightPaymentBl = new FlightPaymentBl();
	@RequestMapping("/")
	public String newRegistration1(){
		
		return "BlackHeader";
	}
	@RequestMapping("/login")
	public ModelAndView newRegistration2(){
	
		//return new ModelAndView("Login","command",new User());
		return new ModelAndView("Login");
	}
	@RequestMapping("/Wallet")
	public String Wallet(){
	
		return "Wallet";
	}
	@RequestMapping("/ContactUs")
	public String contactUs(){
	
		return "ContactUs";
	}
	@RequestMapping("/AboutUs")
	public String aboutUs(){
	
		return "AboutUs";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "Login";
		
	}
	@RequestMapping("/userLogin")
	public String welcomeUser(){
	
		return "UserLogin";
	}
	@RequestMapping("/ConfirmFlightBooking")
	public String confirmFlightBooking(){
	
		return "ConfirmFlightBooking";
	}
	@RequestMapping("/addMoney")
	public String addmoney(){
	
		return "AddMoney";
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
	@RequestMapping(value ="/loginCheck", method = RequestMethod.POST)
	public ModelAndView userLoginCheck(HttpServletRequest request,ModelMap model) throws IOException{
		String username=request.getParameter("username");
        String password=request.getParameter("password");
        
		//String view=null;
		User user=null;
		Admin admin=null;
		ModelAndView mv=null;
		try {
			//user =(User)userBl.checkLogin(user.getUserId(),user.getUserPassword());
			user =(User)userBl.checkLogin(username, password);
			
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
		
			e.printStackTrace();
		}
		try {
			admin = adminBlMMT.checkAdminLogin(username, password);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(user != null){
			model.addAttribute("user", user);
			mv=new ModelAndView("UserDashBoard");

		}
		else if(admin != null){
			model.addAttribute("admin", admin);
			mv=new ModelAndView("AdminDashBoard");
		}
		else{
			mv=new ModelAndView("status");
			
		}
		return mv;
		
		
	}
	@RequestMapping("/searchFlight")
	public String searchFlight(ModelMap model) {
		Flight flight=new Flight();
		model.addAttribute("flight", flight);
		return "FlightForm";
	}
	@RequestMapping("/SelectFlight")
	public ModelAndView SelectFlight(@ModelAttribute("flight") Flight flight,HttpServletRequest request,ModelMap model) {
		String source = request.getParameter("flightSource");
		String destination = request.getParameter("flightDestination");
		String departureDate = request.getParameter("departureDate");
		int  noOfSeats = Integer.parseInt(request.getParameter("seats"));
		model.addAttribute("flightSource", source );
		model.addAttribute("departureDate", departureDate);
		model.addAttribute("flightDestination", destination );
		
		model.addAttribute("noOfSeats", noOfSeats);
		List<Flight> flightList=new ArrayList<Flight>();
		try {
			flightList=flightBookingBlMMT.searchFlight(source, destination);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		ModelAndView mv=null;
		if (flightList.isEmpty()) {
			String message = "No Flights from  " + source + " to " + destination;
			mv=new ModelAndView("NoFlightFromStD","nomessage", message);
		} else 
		{mv=new ModelAndView("LoggedInDisplayAllFlightsStD", "arrayListFlight", flightList);}
		
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
	public String paymentFlight(HttpServletRequest request,HttpSession session,ModelMap model)
	{
		String view=null;
		String promoPickedID = request.getParameter("promoflight");
		double flightTicketPrice = (double) session.getAttribute("flightTicketPrice");
		String flightIDPicked = (String) session.getAttribute("flightId");
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		int noOfSeats = (int) session.getAttribute("noOfSeats");
		double cartValue = 0;
		cartValue = flightPaymentBl.cartValue(flightTicketPrice, noOfSeats);
		double valueAfterPromotion = 0;
		if (promoPickedID.equals("-")) {
			valueAfterPromotion = cartValue;
		}else{
			try {
				valueAfterPromotion = promoBl.applyPromotion( promoBl.searchPromotion(promoPickedID),
						userId, cartValue);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (flightPaymentBl.checkFunds(userId, valueAfterPromotion)){
				model.addAttribute("finalValuetobepaid", valueAfterPromotion);
				view="ConfirmFlightBooking";
			}
			else{
				session.setAttribute("finalValuetobepaid", valueAfterPromotion);
				WalletBlMMT walletBl = new WalletBlMMT();
				double moneyToBeAdded = valueAfterPromotion - (walletBl.walletBalance(userId));
				String message = "Add atleast " + moneyToBeAdded + " to Wallet to book flight seat";
				model.addAttribute("moneyToBeAdded", moneyToBeAdded);
				model.addAttribute("messageFlight", message);
				view="AddMoney";
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		return view;
	}
	@RequestMapping("/MoneyAddded")
	public String moneyAdding(HttpServletRequest request,HttpSession session,ModelMap model){
		double moneyToAdd = Double.parseDouble(request.getParameter("amount"));
		WalletBlMMT walletBl = new WalletBlMMT();
		User user = (User) session.getAttribute("user");
		boolean value = false;
		String msgMoney = "";
		double balance = 0;
		String preciseBalance = null;
		
		try {
			value = walletBl.addWalletMoney(user.getUserId(), moneyToAdd);
		} catch (ClassNotFoundException | SQLException | IOException e) {
		
			e.printStackTrace();
		}
		if (value == true) {
			msgMoney = moneyToAdd+" Successfully Added to Wallet";
			try {
				balance = walletBl.walletBalance(user.getUserId());
				DecimalFormat df2 = new DecimalFormat(".##");
				preciseBalance = df2.format(balance);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("balance", preciseBalance);
			model.addAttribute("msgMoney", msgMoney);
			}
		return "Wallet";
	}
	@RequestMapping("/confirmBookingFlight")
	public String confirmFlightBooking1(HttpSession session,ModelMap model){
		double valueAfterPromotion = (double) session.getAttribute("finalValuetobepaid");
		User user = (User) session.getAttribute("user");
		boolean paymentStatus = false;
		WalletBlMMT walletBlMMT = new WalletBlMMT();
		String view=null;
		try {
			paymentStatus = walletBlMMT.subtractWalletMoney(user.getUserId(), valueAfterPromotion);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		if (paymentStatus) {
			String flightIDPicked = (String) session.getAttribute("flightId");
			System.out.println(flightIDPicked);
			String source = (String) session.getAttribute("flightSource");
			String destination = (String) session.getAttribute("flightDestination");
			int seats = (int) session.getAttribute("noOfSeats");
			try {
				flightBooking = flightBookingBlMMT.bookFlight(user.getUserId(), flightIDPicked, source, destination,
						seats);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				
				e.printStackTrace();
			}
			if (flightBooking != null) {
//				String messageFlight=(String) session.getAttribute("messageFlight");
//				messageFlight=null;
//				model.addAttribute("messageFlight", messageFlight);
				session.removeAttribute("messageFlight");
				model.addAttribute("flightBooking", flightBooking);
				view="FinalFlightStep";
			}
			else{
				try {
					paymentStatus = walletBlMMT.addWalletMoney(user.getUserId(), valueAfterPromotion);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					
					e.printStackTrace();
				}
//				String messageFlight=(String) session.getAttribute("messageFlight");
//				messageFlight=null;
//				model.addAttribute("messageFlight", messageFlight);
				session.removeAttribute("messageFlight");
				view="NoFlightBooking";
			}
		}
		return view;
		
	}
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
	
	
	
	
//	@ModelAttribute("flightBookingID")
//	public String flightBookingIdsent(){
//		return flightBooking.getFlightBookingId();
//		
//	}
	
}
