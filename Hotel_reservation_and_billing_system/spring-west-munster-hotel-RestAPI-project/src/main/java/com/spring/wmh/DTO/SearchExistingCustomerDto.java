package com.spring.wmh.DTO;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SearchExistingCustomerDto {

	
//	@Size(min = 2, message = "LastName should not empty")
	private String customerLastName;
//	@Size(max =10 , message = "contact should be 10")
	private String customerContact;
	
}
