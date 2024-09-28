package com.spring.wmh.service;

import java.util.List;
import java.util.Map;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.SearchExistingCustomerDto;

import jakarta.validation.Valid;

public interface CustomerService {
	
	public Object saveCustomer(int id,  CustomerDTO customerDTO);

	public List<Map<String, Object>> getAllCustomers();
	
	public Object getCustomerById(int id);

	public Object updateCustomerById(int id, CustomerDTO customerDTO);
	
	public Object removeCustomerById(int id);

	public List<Map<String, Object>> searchExistedCustomer( SearchExistingCustomerDto existingCustomerDto);
	
}
