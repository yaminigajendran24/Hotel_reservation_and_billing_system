package com.spring.wmh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.DTO.LoginDto;
import com.spring.wmh.entity.Admin;
import com.spring.wmh.service.AdminService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public Object addAdmin(@RequestBody AdminDTO adminDto){
		return adminService.addAdmin(adminDto);
	}
	
	
	@GetMapping("/getall")
	public List<Map<String, Object>> showAllAdmins() {
		return adminService.getAllAdmins();
	}
	
	
	@PostMapping("/get/{id}")
	public Object showByAdminId(@PathVariable int id) {
		return adminService.getAdminById(id);
	}
	
	
	@PostMapping("/update/{id}")
	public Object updateByAdminId(@PathVariable int id, @RequestBody AdminDTO adminDTO) {
		return adminService.updateAdmin(id, adminDTO);
	}
	
	
	@PostMapping("/delete/{id}")
	public Object removeByAdminId(@PathVariable int id) {
		return adminService.deleteAdminById(id);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> logInvalidation(@Valid @RequestBody LoginDto logDto) {
		return new ResponseEntity<>( adminService.loginValidation(logDto),HttpStatus.OK);
	}
}
