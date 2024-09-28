package com.spring.wmh.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.JoinColumnOrFormula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_table")
public class Payment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "payment_mode", length = 15, nullable = false)
	private String paymentMode;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date", length = 13, nullable = false )
	private LocalDate paymentDate;
	
	@Column(name = "payment_status",length = 10, nullable = false)
	private String paymentStatus;
	
	@Column(name = "card_number", length = 16, nullable = true)
	private String cardNumber;
	
	@Column(name = "cvv", nullable = true)
	private int cvv;
	/*
	 *  foreign key
	 */
	@OneToOne
	@JoinColumn(name = "roomBooking_id", nullable = false)
	private RoomBooking roomBooking;
	/*
	 *   here time and date will updated automated into database
	 */
//	@PrePersist
//	protected void onCreate() {
//		paymentDate= new Date();
//	}
}
