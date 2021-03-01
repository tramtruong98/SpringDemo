package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDetail;
import com.example.demo.entities.Order;

public interface OrderService {
	List<OrderDetail> getListOrders();
	OrderDetail getOrderById(Long id);
	void deleteById(Long id);
	Order addOrder(OrderDetail order);
	//OrderDetail updateOrder(OrderDetail order, Long id);
	//OrderDetail searchOrder(String keyword);
	Long count();

}
