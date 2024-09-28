package com.spring.wmh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.SearchExistingCustomerDto;
import com.spring.wmh.service.CustomerService;

import jakarta.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/getexisting")
	public List<Map<String, Object>> getCustomerByLastNameAndContact(@Valid @RequestBody SearchExistingCustomerDto existingCustomerDto) {

		return customerService.searchExistedCustomer(existingCustomerDto);
	}
	
	@PostMapping("/{id}/addcustomer")
	public Object saveCustomer(@Valid @PathVariable int id,@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomer(id, customerDTO) ;
	}
	
	@GetMapping("/getall")
	public List<Map<String, Object>> getAllCustomer() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/get/{id}")
	public Object getCustomerById(@PathVariable int id ) {
		return customerService.getCustomerById(id);
	}
	
	@PostMapping("/update/{id}")
	public Object updateById(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomerById(id,customerDTO);
	}
	
	@PostMapping("/delete/{id}")
	public Object removeById(@PathVariable int id) {
		return customerService.removeCustomerById(id);
	}
}
