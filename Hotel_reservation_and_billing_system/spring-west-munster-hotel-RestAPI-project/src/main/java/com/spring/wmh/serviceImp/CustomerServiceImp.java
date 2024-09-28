package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.SearchExistingCustomerDto;
import com.spring.wmh.entity.Admin;
import com.spring.wmh.entity.Customer;
import com.spring.wmh.repository.AdminRepository;
import com.spring.wmh.repository.CustomeRepository;
import com.spring.wmh.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomeRepository customeRepository;
	@Autowired
	private AdminRepository adminRepository;


	@Override
	public Object saveCustomer(int id, CustomerDTO customerDTO) { 
		
		Map<String, Object> map =new HashMap<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		Customer eMail = customeRepository.findByCustomerEmail(customerDTO.getCustomerEmail()).orElse(null);
		Customer cNum = customeRepository.findByContactNumber(customerDTO.getContactNumber()).orElse(null);
		
		Admin admin = adminRepository.findById(id).orElse(null);
		
// validate Admin is null or not
		
		if (admin !=null) {
			
//firstName
			if (customerDTO.getCustomerFirstName()!=null) {			
				if (customerDTO.getCustomerFirstName()=="" || customerDTO.getCustomerFirstName().length()<=2 ) {
					map.put("firstName", "first Name Should not be empty must more than 2");	
				}
			} else {
				map.put("firstName", "first Name required");
			}
// lastName
			if (customerDTO.getCustomerLastName()!=null) {				
				if (customerDTO.getCustomerLastName()==""|| customerDTO.getCustomerLastName().length()<=2 ) {
					map.put("lastName", "last Name Should not be emptymust more than 2");	
				}
			} else {
				map.put("lastaName", "last Name required");
			}
// email
			if (customerDTO.getCustomerEmail()!=null) {		
				if (customerDTO.getCustomerEmail()=="") {
					map.put("email", "email Should not be empty");	
				}else {
					if (eMail!=null) {
						map.put("email", "email already existed");	
					}
				}
			} else {
				map.put("email", "email required");
			}
// contact
			if (customerDTO.getContactNumber()!=null) {
				
				if (customerDTO.getContactNumber().length()!=10) {
					map.put("contactNumber", "Contact number Should be 10 digits");	
				}else {
					boolean matches = customerDTO.getContactNumber().matches("\\d+");
					if (matches) {
						if (cNum!=null) {
							map.put("contactNumber", "mobile number already existed");	
						}
					} else {
						map.put("contactNumber", "mobile number must numeric");	
					}
				}
			} else {
				map.put("contactNumber", "first Name required");
			}
// Dob
			if (customerDTO.getDob()!=null) {
				
				if (customerDTO.getDob()=="") {
					map.put("dob", "dob Should not be empty");	
				} else {
					LocalDate today = LocalDate.now();
					LocalDate dob = LocalDate.parse(customerDTO.getDob(), formatter);
					if (dob.isAfter(today)) {
						map.put("dob", "dob  cannot be in the future");
					}
				}
			} else {
				map.put("dob", "dob Name required");
			}
// address1
			if (customerDTO.getAddress1()!=null) {
				
				if (customerDTO.getAddress1()=="") {
					map.put("adress1", "Address-1 Should not be empty");
				}
			} else {
				map.put("adress1", "Address-1 Name required");
			}
// city
			if (customerDTO.getCity()!=null) {
				
				if (customerDTO.getCity()=="") {
					map.put("city", "city Should not be empty");	
				}
			} else {
				map.put("city", "city Name required");
			}
// pincode
			if (customerDTO.getPincode()!=null) {
				
				if (customerDTO.getPincode().length()!=6) {
					map.put("pincode", "pincode Should  be 6 digits");	
				} else {
					boolean matches = customerDTO.getPincode().matches("\\d+");
					if (matches!=true) {						
						map.put("pincode", "pincode must numeric");	
					} 
				}
			} else {
				map.put("pincode", "pincode required");
			}
			
			
			if (map.size()==0) {
				
				Customer customer = new Customer();
				
				customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
				customer.setCustomerLastName(customerDTO.getCustomerLastName());
				customer.setCustomerEmail(customerDTO.getCustomerEmail());
				customer.setContactNumber(customerDTO.getContactNumber());
				
				
				customer.setDob(LocalDate.parse(customerDTO.getDob(),formatter));
				
				customer.setAddress1(customerDTO.getAddress1());
				customer.setAddress2(customerDTO.getAddress2());
				customer.setCity(customerDTO.getCity());
				customer.setPincode(customerDTO.getPincode());
				customer.setCustomerCreatedOn(LocalDate.now());
					
				customer.setAdmin(admin);
					
				customeRepository.save(customer);
				
				map.put("status", "succesfully");
				map.put("customerId", customer.getCustomerId());
			}
			
			return map;
		} else {
			map.put("error", "Admin id not found");
			return map;
		}
		
	}


	@Override
	public List<Map<String, Object>> getAllCustomers() {
		
		List<Map<String, Object>> customerObjs = new ArrayList<Map<String, Object>>();
		
		List<Customer> all = customeRepository.findAll();
		Map<String, Object> map = null;
//	
		for (Customer customer : all) {
			
			map= new HashMap<String, Object>();
			
			map.put("customerName", customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
			map.put("customerEmailId", customer.getCustomerEmail());
			map.put("customerContactNumber", customer.getContactNumber());
			map.put("address1", customer.getAddress1());
			map.put("address2", customer.getAddress2());
			map.put("city", customer.getCity());
			map.put("pincode", customer.getPincode());
			map.put("adminId", customer.getAdmin().getAdminId());
			map.put("adminName", customer.getAdmin().getAdminUserName());
			
			customerObjs.add(map);
		}
		return customerObjs;
	}


	@Override
	public Object getCustomerById(int id) {
		
		Map<String, Object> map = new HashMap<>();
	
		Customer customer = customeRepository.findById(id).orElse(null);
		
		if (customer!=null) {
			
			Map<String, Object> map2 = new HashMap<>();	
			
			map2.put("customerName", customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
			map2.put("customerEmailId", customer.getCustomerEmail());
			map2.put("customerContactNumber", customer.getContactNumber());
			map2.put("address1", customer.getAddress1());
			map2.put("address2", customer.getAddress2());
			map2.put("city", customer.getCity());
			map2.put("incode", customer.getPincode());
			map2.put("adminId", customer.getAdmin().getAdminId());
			map2.put("adminName", customer.getAdmin().getAdminUserName());
			   
			map.put("customerDetails", map2);;
			
		} else {
			map.put("Error", "Customer not found");
		}
		return map;
	}


	@Override
	public Object updateCustomerById(int id, CustomerDTO customerDTO) {
		
		Map<String, Object> map = new HashMap<>();
		CustomerDTO customerDto = new CustomerDTO();
		Customer customer = customeRepository.findById(id).orElse(null);
		
		if (customer!=null) {
		
			customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
			customer.setCustomerLastName(customerDTO.getCustomerLastName());
			customer.setCustomerEmail(customerDTO.getCustomerEmail());
			customer.setContactNumber(customerDTO.getContactNumber());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			customer.setDob(LocalDate.parse(customerDTO.getDob(),formatter));
			
			customer.setAddress1(customerDTO.getAddress1());
			customer.setAddress2(customerDTO.getAddress2());
			customer.setCity(customerDTO.getCity());
			customer.setPincode(customerDTO.getPincode());
			customeRepository.save(customer);
			
					
			map.put("Status", "sucessfully updated" );
			
		} else {
			map.put("Error", " customer id is not found");
		}
		return map;
	}


	@Override
	public Object removeCustomerById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		Customer customer = customeRepository.findById(id).orElse(null);
		
		if (customer!=null) {
			
			customeRepository.deleteById(id);
			map.put("Status", "Data removed Sucessfully...");
			
		} else
			map.put("Error", "customer not found");
		
		return map;
	}


//	@Override
//	public List<Map<String, Object>> searchExistedCustomer(SearchExistingCustomerDto existingCustomerDto) {
//		
//		String customerContact = existingCustomerDto.getCustomerContact();
//		String customerLastName = existingCustomerDto.getCustomerLastName();
//				
////		 Query the database for matching customers
////		if(customerLastName!=null && customerContact==null) {
////		}else if(customerLastName==null && customerContact!=null) {		
////			Customer customer = customeRepository.findByContactNumber(customerLastName).orElse(null);	
////		} else {
////			
////		}
////		List<Customer> customers = customeRepository.findByCustomerLastName(customerLastName);	
//	    List<Customer> customers = customeRepository.findByLastNameOrContact(customerLastName, customerContact);
//	    List<Map<String, Object>> maps = new ArrayList<>();
//	    Map<String, Object> map = new HashMap<>();
//
//	    if (customers.size()!=0) {
//	    	for (Customer customer : customers) {
//	    		
//		        // Populate the map with customer details
//	    		
//		        map.put("customerId", customer.getCustomerId());
//		        map.put("firstName", customer.getCustomerFirstName());
//		        map.put("lastName", customer.getCustomerLastName());
//		        map.put("emailId", customer.getCustomerEmail());
//		        map.put("contactNumber", customer.getContactNumber());
//		        map.put("address1", customer.getAddress1());
//		        map.put("address2", customer.getAddress2());
//		        map.put("city", customer.getCity());
//		        map.put("pincode", customer.getPincode());
//		        map.put("adminId", customer.getAdmin().getAdminId());
//		        map.put("adminName", customer.getAdmin().getAdminUserName());
//		        
//
//		        // Add map to the list
//		        maps.add(map);
//		    }
//		} else {
//			map.put("Error", "Data not found");
//		}
//	    return maps;
//	}
//	
	@Override
	public List<Map<String, Object>> searchExistedCustomer(SearchExistingCustomerDto existingCustomerDto) {

	    String customerContact = existingCustomerDto.getCustomerContact();
	    String customerLastName = existingCustomerDto.getCustomerLastName();

//	    // Query the database for matching customers
//	    List<Customer> customers = customeRepository.findByLastNameOrContact(customerLastName, customerContact);

	    /*
	     *    updated query 
	     */
	    List<Customer> customers = customeRepository.findByLastNameOrContact(
	    	    customerLastName.isEmpty() ? null : customerLastName, 
	    	    customerContact.isEmpty() ? null : customerContact
	    	);
	    List<Map<String, Object>> maps = new ArrayList<>();

	    // Check if any customers were found
	    if (!customers.isEmpty()) {
	        for (Customer customer : customers) {

	            // Create a new map for each customer
	            Map<String, Object> map = new HashMap<>();

	            // Populate the map with customer details
	            map.put("customerId", customer.getCustomerId());
	            map.put("firstName", customer.getCustomerFirstName());
	            map.put("lastName", customer.getCustomerLastName());
	            map.put("emailId", customer.getCustomerEmail());
	            map.put("contactNumber", customer.getContactNumber());
	            map.put("address1", customer.getAddress1());
	            map.put("address2", customer.getAddress2());
	            map.put("city", customer.getCity());
	            map.put("pincode", customer.getPincode());
	            map.put("adminId", customer.getAdmin().getAdminId());
	            map.put("adminName", customer.getAdmin().getAdminUserName());

	            // Add each map to the list
	            maps.add(map);
	        }
	    } else {
	        // If no customers are found, add an error message to the map
	        Map<String, Object> errorMap = new HashMap<>();
	        errorMap.put("Error", "Data not found");
	        maps.add(errorMap);
	    }

	    return maps;
	}
	

}
