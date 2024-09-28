package com.spring.wmh.entity;

import java.util.ArrayList;
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
import lombok.Data;

@Data
@Entity
@Table(name = "Room_type_table")
public class RoomsType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name = "room_type", length = 20)
	private String roomType;
	
	@Column(name = "room_count")
	private int roomCount;
	
	@Column(name = "room_price")
	private double roomPrice;
	
	@Column(name = "max_no_of_people")
	private byte maxNoOfPeople;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<RoomBooking> roomBooking = new ArrayList<RoomBooking>();

}
