package com.microtutorial.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microtutorial.orderservice.dto.Payment;
import com.microtutorial.orderservice.dto.TransactionRequest;
import com.microtutorial.orderservice.dto.TransactionResponse;
import com.microtutorial.orderservice.entity.Order;
import com.microtutorial.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private RestTemplate template;
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		String responseMessage = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		// call to payment service
		Payment paymentResponse = new Payment();
		try {
			paymentResponse= template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		responseMessage = paymentResponse.getPaymentStatus().equals("success")?"payment successful":"paymnet failed";
		
		repository.save(order);
		
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), responseMessage);
	}
}
