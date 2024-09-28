package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;
import com.spring.wmh.DTO.ConfirmationDto;
import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.RoomBookingDTO;
import com.spring.wmh.DTO.RoomTypeDTO;
import com.spring.wmh.entity.Customer;
import com.spring.wmh.entity.RoomBooking;
import com.spring.wmh.entity.RoomsType;
import com.spring.wmh.repository.CustomeRepository;
import com.spring.wmh.repository.RoomBookingRepository;
import com.spring.wmh.repository.RoomsTypeRepository;
import com.spring.wmh.service.RoomBookingService;

@Service
public class RoomBookingServiceImp implements RoomBookingService{

	
	@Autowired
	private RoomBookingRepository roomBookingRepository;
	@Autowired
	private CustomeRepository customeRepository;
	@Autowired
	private RoomsTypeRepository roomsTypeRepository;

	
	/*
	 * Registration   
	 */
	@Override
	public Object saveBooking(int customerId, int roomTypeId, RoomBookingDTO bookingDTO) {
		System.out.println("\n\n"+bookingDTO.getFromDate()+"       "+bookingDTO.getToDate());
		Map<String, Object> map = new HashMap<>();
		
		Customer customer = customeRepository.findById(customerId).orElse(null);
		RoomsType roomsType = roomsTypeRepository.findById(roomTypeId).orElse(null);
	

		if(customer!=null && roomsType!=null) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate today = LocalDate.now();
			if (bookingDTO.getFromDate()==null) {				
				map.put("checkInDate", "check-in should not me null");
			}else {
				if (bookingDTO.getFromDate()=="") {				
					map.put("checkInDate", "check-in date should not be empty");
				} else {
					if (LocalDate.parse(bookingDTO.getFromDate(), formatter).isBefore(today)) {						
						map.put("checkInDate", "check-in date should not be past");
					}
				}
			}
			if (bookingDTO.getToDate()==null) {				
				map.put("checkOutDate", "check-out should not me null");
			}else {
				if (bookingDTO.getToDate()=="") {				
					map.put("checkOutDate", "check-out date should not be empty");
				} else {
					if ((LocalDate.parse(bookingDTO.getToDate(), formatter).isBefore(LocalDate.parse(bookingDTO.getFromDate(), formatter)))) {						
						map.put("checkOutDate", "check out date should not be before lessthen From Date");
					}
				}
			}
			
			if (bookingDTO.getNoOfPeople()<=roomsType.getMaxNoOfPeople() && (bookingDTO.getNoOfPeople()>0)){
				
			} else {
				map.put("noOfPeople", "max people "+roomsType.getMaxNoOfPeople());
			}
			
			// count the no.of dates 
			
			
			if (map.size()==0) {
				
				
				LocalDate fromDate = LocalDate.parse(bookingDTO.getFromDate(), formatter);
				LocalDate toDate = LocalDate.parse(bookingDTO.getToDate(), formatter);
				
				long daysCount = ChronoUnit.DAYS.between(fromDate, toDate);
				System.out.println(daysCount);
				
				if (daysCount == 0) {
					daysCount = 1;
				}
				
				double roomPrice =roomsType.getRoomPrice();
				System.out.println(roomPrice);
				
				
				double price= roomPrice*daysCount;
				System.out.println("Room prce"+price);
				
				RoomBooking roomBooking = new RoomBooking();
				roomBooking.setCustomer(customer);
				roomBooking.setRoom(roomsType);
				roomBooking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
				roomBooking.setCheckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
				roomBooking.setNoOfPeople(bookingDTO.getNoOfPeople());
				roomBooking.setBookedOn(LocalDate.now());
				roomBooking.setAmount(price);
				
				
				roomBookingRepository.save(roomBooking);
				map.put("Status", "booking saved");
				map.put("bookingId", roomBooking.getBookingId());
				
				
			}
		} else {
			
			map.put("errorMsg", "Customer or room details not found");
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getAllBookings() {
		
		
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<RoomBooking> bookings = roomBookingRepository.findAll();	
				
		for (RoomBooking booking : bookings) {
			
			map.put("customerName", booking.getCustomer().getCustomerFirstName());
			map.put("customerLastName", booking.getCustomer().getCustomerLastName());
			map.put("customerContactNumber", booking.getCustomer().getContactNumber());
			map.put("roomType", booking.getRoom().getRoomType());
			map.put("roomPrice", booking.getRoom().getRoomPrice());
			map.put("checkInDate", booking.getCheckInDate().toString());
			map.put("checkOutdate", booking.getCheckOutDate().toString());
			map.put("noOfPeople", booking.getNoOfPeople());
			map.put("roomBookedOn", booking.getBookedOn().toString());
			
			
		}
		
		return maps;
	}

	@Override
	public Object getbookingById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		RoomBooking booking = roomBookingRepository.findById(id).orElseGet(null);
		if (booking!=null) {
			
			map.put("customerName", booking.getCustomer().getCustomerFirstName());
			map.put("customerLastName", booking.getCustomer().getCustomerLastName());
			map.put("customerContactNumber", booking.getCustomer().getContactNumber());
			map.put("roomType", booking.getRoom().getRoomType());
			map.put("totalRoomAmount", booking.getAmount());
			map.put("checkInDate", booking.getCheckInDate().toString());
			map.put("checkOutdate", booking.getCheckOutDate().toString());
			map.put("noOfPeople", booking.getNoOfPeople());
			map.put("roomBookedOn", booking.getBookedOn().toString());
			
		} else {
			map.put("errorMsg", "Data not found on this ID");
		}
		return map;
	}

	@Override
	public Object updateBookIngById(int bookingId, RoomBookingDTO bookingDTO) {
		
		Map<String, Object> map = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		RoomBooking booking = roomBookingRepository.findById(bookingId).orElseGet(null);
		
		if (booking!=null) {
			
//			Customer customer = new Customer();
//			RoomsType roomsType = new RoomsType();
//			
//			booking.setCustomer(customer);
//			booking.setRoom(roomsType);
//			
//			booking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
//			booking.setCHeckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
//			booking.setBookedOn(LocalDate.now());
// 			booking.setNoOfPeople(bookingDTO.getNoOfPeople());
//			
//			roomBookingRepository.save(booking);
//			
//			CustomerDTO customerDTO = new CustomerDTO();
//			
//			customerDTO.setCustomerFirstName(booking.getCustomer().getCustomerFirstName());
//			customerDTO.setContactNumber(booking.getCustomer().getContactNumber());
//			
//			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
//			
//			roomsType.setRoomType(booking.getRoom().getRoomType());
//			roomsType.setRoomPrice(booking.getRoom().getRoomPrice());
//			roomsType.setRoomType(booking.getRoom().getRoomType());
//			
//			RoomBookingDTO bookingDto = new RoomBookingDTO();
//			bookingDto.setCustomerDto(customerDTO);
//			bookingDto.setRoom(roomTypeDTO);
//			bookingDto.setFromDate(booking.getCheckInDate().toString());
//			bookingDto.setToDate(booking.getCHeckOutDate().toString());
//			bookingDto.setNoOfPeople(booking.getNoOfPeople());
//			bookingDto.setBookedOn(booking.getBookedOn().toString());
			
			
			booking.setCustomer(booking.getCustomer());
			booking.setRoom(booking.getRoom());
			booking.setCheckInDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
			booking.setCheckOutDate(LocalDate.parse(bookingDTO.getToDate(), formatter));
//			booking.setBookedOn(LocalDate.now());
 			booking.setNoOfPeople(bookingDTO.getNoOfPeople());
			
			roomBookingRepository.save(booking);
			
			map.put("bookingDetails", "Updated");
		} else {
			map.put("Error", "Id not found");
		}
		return map;
		
		
	}

	@Override
	public Object deleteBookingInfoById(int id) {

		Map<String, Object> map = new HashMap<>();
		
		RoomBooking booking = roomBookingRepository.findById(id).orElse(null);
		if (booking!=null) {
			roomBookingRepository.deleteById(id);
			
			map.put("booking", "Deleted");
		} else {
			map.put("Error", "Id not found");
		}
		return map;
	}

	@Override
	public Object bookingConformation(int bookingId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		RoomBooking booking = roomBookingRepository.findById(bookingId).orElse(null);
		if (booking!=null) {			
			
//			ConfirmationDto confirmationDto = new ConfirmationDto();
			
			
			map.put("customerName", booking.getCustomer().getCustomerFirstName()+" "+booking.getCustomer().getCustomerLastName());
			map.put("roomType", booking.getRoom().getRoomType());
			map.put("fromDate", booking.getCheckInDate().toString());
			map.put("toDate", booking.getCheckOutDate().toString());
			map.put("noOfPeople", booking.getNoOfPeople());
			map.put("price", booking.getAmount());
			map.put("confirmation", true);
			
		} else {
			map.put("Error", "Booking details not found");
		}
		
		return map;
	}
	
	
	
}
