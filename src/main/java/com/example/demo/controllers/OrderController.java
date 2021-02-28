package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@GetMapping("/orders")  
	public List<Order> getAllBooks()   
	{  
	return orderService.getListOrder();  
	}  
	@GetMapping("/order/{id}")  
	public Order findOrderById(@PathVariable Long id)   
	{  
	return orderService.getOrderById(id);  
	}

}
