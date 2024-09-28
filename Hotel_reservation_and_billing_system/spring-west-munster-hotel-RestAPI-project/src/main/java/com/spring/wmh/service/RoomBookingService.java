package com.spring.wmh.service;

import java.util.List;
import java.util.Map;

import com.spring.wmh.DTO.RoomBookingDTO;




public interface RoomBookingService {

	
	public Object saveBooking(int customerId, int roomTypeId, RoomBookingDTO bookingDTO);
	
	public  List<Map<String, Object>> getAllBookings();
	
	public Object getbookingById(int id);
	
	public Object updateBookIngById(int bookingId, RoomBookingDTO bookingDTO);
	
	public Object deleteBookingInfoById(int id);
	
	Object bookingConformation(int bookingId);
}
