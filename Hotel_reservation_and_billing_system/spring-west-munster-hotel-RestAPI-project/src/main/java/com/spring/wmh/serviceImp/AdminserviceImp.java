package com.spring.wmh.serviceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.wmh.DTO.AdminDTO;
import com.spring.wmh.DTO.LoginDto;
import com.spring.wmh.entity.Admin;
import com.spring.wmh.repository.AdminRepository;
import com.spring.wmh.service.AdminService;

@Service
public class AdminserviceImp implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Object addAdmin(AdminDTO adminDTO) {
		
		Map<String, Object> map = new HashMap<>();
		
		Admin userName = adminRepository.findByadminUserName(adminDTO.getAdminUserName()).orElse(null);
		
		if(userName==null) {
			
			Admin admin = new Admin(); // Admin entity Object  
			
			admin.setAdminUserName(adminDTO.getAdminUserName());
			admin.setAdminPassword(adminDTO.getAdminPassword());
			admin.setAdminCreatedOn(LocalDate.now());
			
			adminRepository.save(admin);
			
			map.put("Admin", "added sucessfully");
		} else {
			map.put("adminName", "Name already taken");
		}
		
		return map;
	}

	@Override
	public List<Map<String, Object>> getAllAdmins() {
		
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Admin> admins = adminRepository.findAll();
		
		for (Admin admin : admins) {
			
			map.put("adminId", admin.getAdminId());
			map.put("adminName", admin.getAdminUserName());
			
			maps.add(map);
		}
		
		return maps;
	}
	
	
	@Override
	public Object getAdminById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		Admin admin = adminRepository.findById(id).orElse(null);
		
		if (admin!=null) {
//			
			map.put("adminId", admin.getAdminId());
			map.put("adminName", admin.getAdminUserName());
			
		} else {
			map.put("Error", "Admin not found");
		}
		return map;
	}
	
	
	@Override
	public Object updateAdmin(int id, AdminDTO adminDTO) {
		
		Map<String, Object> map = new HashMap<>();
		
		Admin admin = adminRepository.findById(id).orElse(null);
		
		if (admin!=null) {
//			
			admin.setAdminUserName(adminDTO.getAdminUserName());
			admin.setAdminPassword(adminDTO.getAdminPassword());
//			admin.setAdminCreatedOn(LocalDate.now());
			adminRepository.save(admin);
			
			map.put("Status", "Admin updated");
			
		} else {
			map.put("Error", "Admin not found");
		}
		return map;
		  
	}
	
	@Override
	public Object deleteAdminById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		
		 Admin admin = adminRepository.findById(id).orElse(null);
		 
		 if (admin!=null) {
			 adminRepository.delete(admin);
			 map.put("Status", "Admin Removed");
		} else {
			map.put("Error", "admin id not found");
		}
		 return map;
	}

	
	
	@Override
	public Object loginValidation(LoginDto loginDto) {
		
		Map<String, Object> map = new HashMap<>();
		
		Admin admin = adminRepository.findByadminUserName(loginDto.getUserName()).orElse(null);
		if(admin!=null){
			if (loginDto.getUserName().equals(admin.getAdminUserName()) && loginDto.getPassword().equals(admin.getAdminPassword())) {
		
				map.put("adminId", admin.getAdminId());
				map.put("login", "login successful");
				
//				map.pu)
			}else {			
				map.put("login", "Invalid details");
			}
		} else {
			map.put("login", "invalid details");
		}
		return map;
	}

}
