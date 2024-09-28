package com.spring.wmh.service;

import java.util.List;
import java.util.Map;

import com.spring.wmh.DTO.PaymentDTO;

public interface PaymentService {

	
		public Object savePayment(int id, PaymentDTO paymentDTO);

		public List<Map<String, Object>> getAllPayment();

		public Object getPaymentById(int id);

		public Object updatePaymentById(int id, PaymentDTO paymentDTO);

//		public Object removePaymentById(int id);
}
