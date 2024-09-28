package com.spring.wmh.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RoomTypeDTO {


	private String roomType;
	private int roomCount;
	private double roomPrice;
	private byte maxNoOfPeople;

}
