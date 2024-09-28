package com.spring.wmh.DTO;

import lombok.Data;

@Data
public class ConfirmationDto {

	private String customerName;
	private String roomType;
//	private String bookingType;
	private String checkInDate;
	private String checkOut;
	private int noOfPeople;
	private String price;
	private String confirmation;
	
}
