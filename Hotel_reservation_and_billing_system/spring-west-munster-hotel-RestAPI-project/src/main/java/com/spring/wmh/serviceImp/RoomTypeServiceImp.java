package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.CustomerDTO;
import com.spring.wmh.DTO.RoomTypeDTO;
import com.spring.wmh.entity.RoomsType;
import com.spring.wmh.repository.RoomsTypeRepository;
import com.spring.wmh.service.RoomTypeService;

@Service
public class RoomTypeServiceImp implements RoomTypeService {

	@Autowired
	private RoomsTypeRepository roomsTypeRepository;
	
	@Override
	public Object saveRoom(RoomTypeDTO roomTypeDTO) {
		
		Map<String, Object> map = new HashMap<>();
		
		if (roomTypeDTO.getRoomType()==null) {				
			map.put("roomType", "RoomType should not me null");
		}else {
			if (roomTypeDTO.getRoomType().length()==0) {				
				map.put("roomType", "RoomType date should not be empty");
			}
		}
		
		if (roomTypeDTO.getRoomPrice()==0) {				
			map.put("roomPrice", "RoomPrice should not be empty");
		} 
		if (roomTypeDTO.getMaxNoOfPeople()!=0 && roomTypeDTO.getMaxNoOfPeople()>6 ) {				
			map.put("maxPeople", "maximum people 6");
		}
		
		if(map.size()==0) {
		
			RoomsType roomsType = new RoomsType();
			roomsType.setRoomType(roomTypeDTO.getRoomType());
			roomsType.setRoomPrice(roomTypeDTO.getRoomPrice());
			roomsType.setRoomCount(roomTypeDTO.getRoomCount());
			roomsType.setMaxNoOfPeople(roomTypeDTO.getMaxNoOfPeople());	
			roomsTypeRepository.save(roomsType);
			map.put("room", "added sucessfully");
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getAllRooms() {
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> map=null;
		
		List<RoomsType> rooms = roomsTypeRepository.findAll();
		for (RoomsType roomsType : rooms) {
			map = new HashMap<String, Object>();
			
			map.put("roomId", roomsType.getRoomId());
			map.put("roomType", roomsType.getRoomType());
			map.put("roomPrice", roomsType.getRoomPrice());
			map.put("roomCount", roomsType.getRoomCount());
			map.put("maxPeople", roomsType.getMaxNoOfPeople());
			
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Object getRoombyId(int id) {
		Map<String, Object> map = new HashMap<>();
		
		RoomsType roomsType = roomsTypeRepository.findById(id).orElse(null);
		if (roomsType!= null) {
			
			map.put("roomId", roomsType.getRoomId());
			map.put("roomType", roomsType.getRoomType());
			map.put("roomPrice", roomsType.getRoomPrice());
			map.put("roomCount", roomsType.getRoomCount());
			map.put("maxPeople", roomsType.getMaxNoOfPeople());
			
		} else {
			map.put("Error", "Room id not found");
		}
		
		return map;
	}

	@Override
	public Object updatedRoombyId(int id, RoomTypeDTO roomTypeDTO) {
		
		Map<String, Object> map = new HashMap<>();
		
		RoomsType roomsType = roomsTypeRepository.findById(id).orElseGet(null);
		
		if (roomsType!= null) {
			
			roomsType.setRoomType(roomTypeDTO.getRoomType());
			roomsType.setRoomPrice(roomTypeDTO.getRoomPrice());
			roomsType.setRoomCount(roomTypeDTO.getRoomCount());
			roomsType.setMaxNoOfPeople(roomTypeDTO.getMaxNoOfPeople());
			roomsTypeRepository.save(roomsType);
			
			map.put("room", "updated sucessfully");
		} else {
			map.put("Error", "Room id not found");
		}
		
		return map;
	}

	@Override
	public Object removeRoom(int id) {
		Map<String, Object> map = new HashMap<>();
		
		RoomsType roomsType = roomsTypeRepository.findById(id).orElseGet(null);
		
		if (roomsType!= null) {
			roomsTypeRepository.deleteById(id);
			
			map.put("room", "data removed successufully");
		} else {
			map.put("Error", "Room id not found");
		}
		
		return map;
	}

	
	
	
	

}
