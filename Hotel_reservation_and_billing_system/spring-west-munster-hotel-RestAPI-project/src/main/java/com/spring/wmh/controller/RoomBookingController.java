package com.spring.wmh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.RoomBookingDTO;
import com.spring.wmh.DTO.RoomTypeDTO;
import com.spring.wmh.service.RoomBookingService;

@CrossOrigin
@RestController
@RequestMapping("/room/booking")
public class RoomBookingController {

	@Autowired
	RoomBookingService roomBookingService;
	
	@PostMapping("/{roomTypeId}/bookon/{customerId}/save")
	public Object saveCustomer(@PathVariable int customerId, @PathVariable int roomTypeId, @RequestBody RoomBookingDTO RoomBookingDTO) {
		return roomBookingService.saveBooking(customerId,roomTypeId, RoomBookingDTO) ;
	}
	
	@GetMapping("/getall")
	public List<Map<String, Object>> getAllCustomer() {
		return roomBookingService.getAllBookings();
	}
	
	@PostMapping("/get/{id}")
	public Object getCustomerById(@PathVariable int id ) {
		return roomBookingService.getbookingById(id);
	}
	
	@PostMapping("/update/{bookingId}")
	public Object updateById(@PathVariable int bookingId, @RequestBody RoomBookingDTO bookingDTO) {
		return roomBookingService.updateBookIngById(bookingId, bookingDTO);
	}
//	
	@PostMapping("/delete/{id}")
	public Object removeById(@PathVariable int id) {
		return roomBookingService.deleteBookingInfoById(id);
	}
	
	@PostMapping("/{id}/status")
	public Object confirmation(@PathVariable int id) {
		return roomBookingService.bookingConformation(id);
	}
}
