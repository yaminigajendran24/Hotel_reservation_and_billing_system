package com.spring.wmh.entity;

import java.time.LocalDate;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "Admin_table")
public class Admin {
	
	 @Id
	 @Column(name = "admin_id")
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int adminId;
	 
	 @Column(name = "admin_user_name", length = 20, nullable = false, unique = true)
	 private String adminUserName;
	 
	 @Column(name = "admin_password", length = 20, nullable = false)
	 private String adminPassword;
	 
//	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "admin_created_date")	 
	 private LocalDate adminCreatedOn;
	 
	 @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 private List<Customer> customer= new ArrayList<Customer>();
	 
//	 @PrePersist
//	 protected void onCreate() {
//	 adminCreatedOn = new Date(); // Automatically set the current date when the user is created
//	}
}
