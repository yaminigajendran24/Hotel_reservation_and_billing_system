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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Room_booking_table")
public class RoomBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booking_id")
	private int bookingId;
	
//	@Temporal(TemporalType.DATE)
	@Column(name = "check_in_date",nullable = false)
	private LocalDate checkInDate;
	
//	@Temporal(TemporalType.DATE)
	@Column(name = "check_out_date", nullable = false)
	private LocalDate checkOutDate;
	
	@Column(name = "no_of_people")
	private byte noOfPeople;
	
	@Column(name = "amount")
	private double amount;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "booking_date", nullable = false)
	private LocalDate bookedOn;
	
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private RoomsType room;
	
	@OneToOne(mappedBy = "roomBooking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	
//	/*
//	 *   to recode the automated date and time when the recode has inserted into the Table
//	 */
//	@PrePersist
//	protected void onCreate() {
//		bookedOn=new Date();
//	}
}
