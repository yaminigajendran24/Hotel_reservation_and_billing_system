package com.spring.wmh.service;

import java.util.List;
import java.util.Map;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.RoomTypeDTO;

public interface RoomTypeService {

	public Object saveRoom(RoomTypeDTO roomTypeDTO);

	public List<Map<String, Object>> getAllRooms();
	
	public Object getRoombyId(int id);

	public Object updatedRoombyId(int id, RoomTypeDTO roomTypeDTO);
	
	public Object removeRoom(int id);
}
