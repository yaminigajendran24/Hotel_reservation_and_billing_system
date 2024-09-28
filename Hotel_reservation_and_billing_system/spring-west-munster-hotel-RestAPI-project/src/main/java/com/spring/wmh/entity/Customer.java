package com.spring.wmh.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="customer_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "customer_first_name", length = 45, nullable = false)
	private String customerFirstName;
	
	@Column(name = "customer_last_name", length = 45, nullable = false)
	private String customerLastName;
	
	@Column(name = "customer_email_id", length = 45, nullable = false, unique = true)
	private String customerEmail;
	
	@Column(name = "customer_contact_number", length = 13, nullable = false, unique = true)
	private String contactNumber;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "customer_DoB", nullable = false)
	private LocalDate dob;
	
	@Column(name = "address_1", length = 45, nullable = false)
	private String address1;
	
	@Column(name = "address_2", length = 45)
	private String address2;
	
	@Column(name = "city", length = 20, nullable = false)
	private String city;
	
	@Column(name = "pincode", length = 7, nullable = false)
	private String pincode;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private LocalDate customerCreatedOn;

	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RoomBooking> roomBooking = new ArrayList<RoomBooking>();

	
	@ManyToOne
	@JoinColumn(name = "admin_id", nullable = false)
	private Admin admin;
}
