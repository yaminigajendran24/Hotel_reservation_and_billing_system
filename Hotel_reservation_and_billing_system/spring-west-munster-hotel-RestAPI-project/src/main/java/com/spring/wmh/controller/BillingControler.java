package com.spring.wmh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.RoomBookingDTO;
import com.spring.wmh.service.BillingService;

@CrossOrigin
@RestController
@RequestMapping("/billing")
public class BillingControler {

	@Autowired
	private BillingService billingService;
	
	
	@PostMapping("/finalBill/{paymentId}")
	public Object finalBill(@PathVariable int paymentId) {
		return billingService.roomBill(paymentId) ;
	}
}
