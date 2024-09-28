package com.spring.wmh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.DTO.RoomTypeDTO;
import com.spring.wmh.repository.RoomsTypeRepository;
import com.spring.wmh.service.RoomBookingService;
import com.spring.wmh.service.RoomTypeService;



@CrossOrigin
@RestController
@RequestMapping("/room/type")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;
	

	@PostMapping("/save")
	public Object addAdmin(@RequestBody RoomTypeDTO roomTypeDTO){
		return roomTypeService.saveRoom(roomTypeDTO);
	}
	
	
	@GetMapping("/getall")
	public List<Map<String, Object>> showAllAdmins() {
		return roomTypeService.getAllRooms();
	}
	
	
	@PostMapping("/get/{id}")
	public Object showByAdminId(@PathVariable int id) {
		return roomTypeService.getRoombyId(id);
	}
	
	
	@PostMapping("/update/{id}")
	public Object updateByAdminId(@PathVariable int id, @RequestBody RoomTypeDTO roomTypeDTO) {
		return roomTypeService.updatedRoombyId(id, roomTypeDTO);
	}
	
	@PostMapping("/delete/{id}")
	public Object removeByAdminId(@PathVariable int id) {
		return roomTypeService.removeRoom(id);
	}
}
