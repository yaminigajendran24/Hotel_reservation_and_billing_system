package com.spring.wmh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.wmh.entity.Admin;
import com.spring.wmh.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Optional<Payment> findByCardNumber(String cardNumber);
}
