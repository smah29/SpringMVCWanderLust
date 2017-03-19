package com.mmt.newController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.model.bean.Flight;
import com.mmt.model.bean.Hotel;
import com.mmt.model.bean.HotelRoom;
import com.mmt.model.bean.Promotion;
import com.mmt.model.bean.User;
import com.mmt.model.bl.HotelBlMMT;
import com.mmt.model.bl.PromotionBlMMT;
import com.mmt.model.bl.WalletBlMMT;

@SessionAttributes({"user","moneyToBeAdded","balance","msgMoney","finalValuetobepaid","noOfRooms","din","dout","duration","place","from","to"})
@Controller
public class HotelController {

	private HotelBlMMT hotelBlMMT = new HotelBlMMT();
	
	@RequestMapping("/searchHotel")
	public String searchHotel(ModelMap model) {
		Hotel hotel=new Hotel();
		model.addAttribute("hotel", hotel);
		return "HotelForm";
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
	@RequestMapping("/SearchHotelByPlace")
	public ModelAndView searchHotelByPlace(@ModelAttribute("hotel") Hotel hotel,HttpServletRequest request,ModelMap model) {
		String place = request.getParameter("hotelLocation");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String rooms=request.getParameter("room");
		int noOfRooms=Integer.parseInt(rooms);
		model.addAttribute("noOfRooms", noOfRooms);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = (Date) dateFormat.parse(from);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		Date d2 = null;
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d2 = (Date) dateFormat1.parse(to);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		model.addAttribute("din", d1);
		model.addAttribute("dout", d2);
		long diff = d2.getTime() - d1.getTime();
		int duration = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		model.addAttribute("duration", duration);
		model.addAttribute("place", place);
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		ArrayList<Hotel> arrayListHotel = null;
		List<Flight> flightList=new ArrayList<Flight>();
		try {
			arrayListHotel = hotelBlMMT.searchHotel1(place);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		ModelAndView mv=null;
		if (arrayListHotel.isEmpty()) {
			String message = "No Hotels found in " + place;
			 mv=new ModelAndView("NoHotelInPlace","message",message);
			
		} else {
			mv=new ModelAndView("DisplayAllHotelPlace","arrayListHotel", arrayListHotel);
			

		}
		
		return mv;
	}
	
	@RequestMapping("/ChooseRoom")
	public ModelAndView searchHotelByPlace(HttpSession session,ModelMap model){
		String hotelId = (String) session.getAttribute("hotelId");
		ArrayList<HotelRoom> arrayListHotelRoom = null;
		try {
			arrayListHotelRoom = hotelBlMMT.displayAvailHotelRoom(hotelId);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		ModelAndView mv=null;
		if (arrayListHotelRoom.isEmpty()) {
			String message = "No Hotel Rooms Available";
			mv=new ModelAndView("NoHotelRoomAvailable","message",message);
		} else {
			//session.setAttribute("arrayListHotelRoom", arrayListHotelRoom);
			mv=new ModelAndView("ChooseRoom","arrayListHotelRoom", arrayListHotelRoom);
		}
		return mv;
		}
	
	@RequestMapping("/DisplayWallet")
	public String displayWallet(HttpSession session,ModelMap model){
		WalletBlMMT walletBl = new WalletBlMMT();
		String preciseBalance = null;
		double balance = 0;
		User user = (User) session.getAttribute("user");
		
			try {
				balance = walletBl.walletBalance(user.getUserId());
			} catch (ClassNotFoundException | SQLException | IOException e) {
			
				e.printStackTrace();
			}
			DecimalFormat df2 = new DecimalFormat(".##");
			preciseBalance = df2.format(balance);
		
		model.addAttribute("balance", preciseBalance);
	
		return "Wallet";
		
	}
	
	@RequestMapping("/ChoosePromoHotel")
	public ModelAndView choosePromoHotel(HttpSession session,ModelMap model){
		ModelAndView mv=null;
		PromotionBlMMT promoBl = new PromotionBlMMT();
		ArrayList<Promotion> arrayListPromoHotel = null;
		try {
			arrayListPromoHotel = promoBl.displayPromotion("HOTEL");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv=new ModelAndView("ChoosePromoCodeHotel","arrayListPromoHotel", arrayListPromoHotel);
		return mv;
	}
}
