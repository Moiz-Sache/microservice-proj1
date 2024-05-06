package com.microtutorial.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microtutorial.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
