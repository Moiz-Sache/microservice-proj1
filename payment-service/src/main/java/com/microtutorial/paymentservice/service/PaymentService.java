package com.microtutorial.paymentservice.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microtutorial.paymentservice.entity.Payment;
import com.microtutorial.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepository.save(payment);
	}
	
	public String paymentProcessing() {
		//return payment status based on 3rd party api gateway call
		return new Random().nextBoolean()?"success":"failed";
	}

	public Payment getPaymentByOrderId(int orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
}
