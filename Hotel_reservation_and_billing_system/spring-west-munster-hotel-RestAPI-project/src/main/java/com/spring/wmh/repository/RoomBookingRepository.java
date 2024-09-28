package com.spring.wmh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.RoomBooking;


@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

}
