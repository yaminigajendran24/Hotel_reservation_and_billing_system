package com.spring.wmh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {


	Optional<Admin> findByadminUserName(String adminUserName);
	
}
