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

import com.spring.wmh.DTO.PaymentDTO;
import com.spring.wmh.service.PaymentService;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping("/{id}/addpayment")
	public Object savePayment(@PathVariable int id,@RequestBody PaymentDTO paymentDTO) {
	return paymentService.savePayment(id,paymentDTO);

	}


	@GetMapping("/getall")
	public List<Map<String, Object>> getAllPayment(){
	return paymentService.getAllPayment();
	}


	@PostMapping("/get/{id}")
	public Object getPaymentById(@PathVariable int id) {
	return paymentService.getPaymentById(id);
	}


	@PostMapping("/update/{id}")
	public Object updateById(@PathVariable int id,@RequestBody PaymentDTO paymentDTO) {

	return paymentService.updatePaymentById(id,paymentDTO);

	}

//	@PostMapping("/delete/{id}")
//	public Object removeById(@PathVariable int id) {
//	return  paymentService.removePaymentById(id);
//	}

}
