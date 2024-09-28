package com.spring.wmh.serviceImp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.entity.Payment;
import com.spring.wmh.repository.PaymentRepository;
import com.spring.wmh.service.BillingService;
@Service
public class BillingServiceImp implements BillingService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public Object roomBill(int id) {
		Map<String, Object> map = new HashMap<>();
		
		Payment payment = paymentRepository.findById(id).orElse(null);
		
		if (payment!=null) {
			
			map.put("bookingNo:", payment.getRoomBooking().getBookingId());
			map.put("curtomerName", payment.getRoomBooking().getCustomer().getCustomerFirstName()+" "+
					payment.getRoomBooking().getCustomer().getCustomerLastName());
			map.put("customerAddress", payment.getRoomBooking().getCustomer().getAddress1()+" "+
					payment.getRoomBooking().getCustomer().getCity()+" "+
					payment.getRoomBooking().getCustomer().getPincode());
			map.put("roomType", payment.getRoomBooking().getRoom().getRoomType());
			
			double roomPrice = payment.getRoomBooking().getRoom().getRoomPrice();

			double totalPrice = roomPrice+1000;
			map.put("price", roomPrice);
			map.put("totalBill", totalPrice);
			map.put("paymentStatus", payment.getPaymentStatus());
			
			
			
		} else {
			map.put("Error", "Payment details not found");
		}
		return map;
	}

}
