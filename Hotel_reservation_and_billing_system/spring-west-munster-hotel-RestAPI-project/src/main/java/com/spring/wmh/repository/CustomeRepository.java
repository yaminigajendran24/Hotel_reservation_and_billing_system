package com.spring.wmh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.Customer;

@Repository
public interface CustomeRepository extends JpaRepository<Customer, Integer> {
//
//	
//	@Query("SELECT c FROM Customer c WHERE c.customerLastName LIKE CONCAT(:lastName, '%') OR c.contactNumber LIKE CONCAT(:contact, '%')")
	@Query("SELECT c FROM Customer c WHERE "
		     + "(COALESCE(:lastName, '') = '' OR c.customerLastName LIKE CONCAT(:lastName, '%')) "
		     + "AND (COALESCE(:contact, '') = '' OR c.contactNumber LIKE CONCAT(:contact, '%'))")
	List<Customer> findByLastNameOrContact(@Param("lastName") String lastName, @Param("contact") String contact);
	
	Optional<Customer> findByCustomerEmail(String customerEmail);
	
	List<Customer> findByCustomerLastName(String customerLastName);
	
	Optional<Customer> findByContactNumber(String contactNumber);
}
