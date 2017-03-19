package com.mmt.newController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.model.bean.Flight;
import com.mmt.model.bean.FlightBooking;
import com.mmt.model.bean.HotelBooking;
import com.mmt.model.bean.User;
import com.mmt.model.bl.FlightBookingBlMMT;
import com.mmt.model.bl.UserBlMMT;
@SessionAttributes("user")
@Controller
public class PastBookings {
	private static Logger logger=Logger.getLogger(PastBookings.class);
	UserBlMMT blMMT = new UserBlMMT();
	@RequestMapping("/UserPastFlight")
	public ModelAndView pastFlights(HttpSession session){
		
		User user = (User) session.getAttribute("user");
		ArrayList<FlightBooking> bookedFlightList = null;
		try {
			bookedFlightList = blMMT.pastFbooking(user.getUserId());
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView("UserPastFlight","bookedFlightList",bookedFlightList);
		BasicConfigurator.configure();
	 	logger.info("UserPastFlight"); 
		return mv;
		}
	@RequestMapping("/UserPastHotel")
	public ModelAndView pastHotels(HttpSession session){
		UserBlMMT blMMT = new UserBlMMT();
		User user = (User) session.getAttribute("user");
		ArrayList<HotelBooking> bookedHotelList = null;
		try {
			 bookedHotelList = blMMT.pastHbooking(user.getUserId());
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView("UserPastHotel","bookedHotelList",bookedHotelList);
		logger.info("UserPastHotel");
		return mv;
		}
	@RequestMapping("/ViewPastFlight")
	public ModelAndView viewDetailFlight(HttpSession session){
		Flight flight=new Flight();
		FlightBookingBlMMT flightBookingBlMMT=new FlightBookingBlMMT();
		try {
			flight=flightBookingBlMMT.searchFlight((String) session.getAttribute("viewFlightId"));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView("PrintFlightTicket","viewedFlightDetails",flight);
		logger.info("PrintFlightTicket");
		return mv;
		}
	@RequestMapping("/UserProfile")
	public String profileUser(HttpSession session){
		User user = (User) session.getAttribute("user");
		logger.info("UserProfile");
		return "UserProfile";
		
	}
}
