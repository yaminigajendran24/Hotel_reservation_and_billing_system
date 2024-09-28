package com.spring.wmh.DTO;

import java.util.Date;

import com.spring.wmh.entity.RoomBooking;

import lombok.Data;

@Data
public class PaymentDTO {

	
	private String paymentMode;
	private String paymentDate;
	private String paymentStatus;
	private String cardNumber;
	private String cvv;
	private RoomBookingDTO roomBooking;
	
}
