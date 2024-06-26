package com.microtutorial.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/orderFallback")
	public Mono<String> orderServiceFallback(){
		return Mono.just("Order service is taking too long to respond or down. Please try again later");
	}
	
	@RequestMapping("/paymentFallback")
	public Mono<String> paymentServiceFallback(){
		return Mono.just("Payment service is taking too long to respond or down. Please try again later");
	}
}
