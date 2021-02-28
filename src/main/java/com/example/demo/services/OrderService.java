package com.example.demo.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.entities.Order;
@Service
public interface OrderService {
	List<Order> getListOrder();
	Order getOrderById(Long id);
	void deleteById(Long id);
	Order createOrUpdateOrder(Order order);
	Order searchOrder(String keyword);

}
