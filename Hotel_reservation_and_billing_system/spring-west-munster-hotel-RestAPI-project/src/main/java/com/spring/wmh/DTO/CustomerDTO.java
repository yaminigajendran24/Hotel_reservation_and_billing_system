package com.spring.wmh.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomerDTO {
	
	
	private String customerFirstName;
	
	@NotNull(message = "customer lastname required")
	@Size(min = 1, message = "lastname should not empty")
	private String customerLastName;
	
	@NotNull(message = "customer email required")
	@Size(min = 1, message = "email should not empty")
	private String customerEmail;
	
	@NotNull(message = "customer contact number required")
	@Size(min = 1, message = "contact should not empty")
	private String contactNumber;
	
	@NotNull(message = "customer dob required")
	@Size(min = 1, message = "dob should not empty")	
	private String dob;
	
	@NotNull(message = "customer address1 required")
	@Size(min = 1, message = "address1 should not empty")	
	private String address1;
	
	private String address2;
	
	@NotNull(message = "customer city required")
	@Size(min = 1, message = "city should not empty")	
	private String city;
	
	@NotNull(message = "customer pincode required")
	@Size(min = 6, message = "pincode should not empty")	
	private String pincode;

	private AdminDTO admin;
	
}

//@Valid
//
//
//
//
//
//@NotNull(message ="FirstName is Mandatory")
//
//@Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
//
//private String customerFirstName;
//
//
//
//@NotNull(message="LastName is Mandatory")
//
//@Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
//
//private String customerLastName;
//
//
//
//@NotBlank(message ="Email is Mandatory")
//
//@Email(message ="Enail Should be Valid")
//
//private String customerEmail;
//
//
//
//@NotBlank(message ="Mobile Number is Mandatory")
//
//@Size(min = 10, message = "Customer Mobile Number")
//
//private String contactNumber;
//
//
//
//@NotBlank(message ="Date of Birth is Mandatory")
//
//private LocalDate dob;
//
//
//
//@NotBlank(message ="Address1 is Mandatory")
//
//@Size(min = 30, max =50, message = "Customer Address1 min 30 words")
//
//private String address1;
//
//
//
//private String address2;
//
//
//
//@NotBlank(message ="City is Mandatory")
//
//@Size(min = 10, max =50, message = "Customer City ")
//
//private String city;
//
//
//
//@NotBlank(message ="Pincode is Mandatory")
//
//@Pattern(regexp = "\\d{6}", message ="Pincode must be exactly 6 digits")
//
//private String pincode;
//
//
//
////private AdminDTO admin;
