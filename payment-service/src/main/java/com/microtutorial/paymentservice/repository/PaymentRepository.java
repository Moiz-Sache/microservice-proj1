package com.microtutorial.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microtutorial.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderId(int orderId);

}
