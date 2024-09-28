package com.spring.wmh.service;

import java.util.List;
import java.util.Map;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.DTO.LoginDto;

public interface AdminService {

	public Object addAdmin(AdminDTO adminDto);

	public List<Map<String, Object>> getAllAdmins();
	
	public Object getAdminById(int id);
	
	public Object updateAdmin(int id, AdminDTO adminDTO);
	
	public Object deleteAdminById(int id);
	
	public Object loginValidation(LoginDto loginDto);
	
	
	
}
