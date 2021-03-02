package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Order;
import com.example.demo.repositories.CRUDRepository;
@Component
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private CRUDRepository orderRepository;
	
	@Override
	public List<Order> getListOrder() {
		List<Order> orders = new ArrayList<>(); 
		orderRepository.findAll().forEach(order -> orders.add(order));
		return orders;
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}


	@Override
	public Order searchOrder(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		return orderRepository.count();
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order, Long id) {
		return orderRepository.findById(id)
			      .map(ord -> {
			    	  ord.setAmount(order.getAmount());
			    	  ord.setTotalPrice(order.getTotalPrice());
			    	  ord.setCustomer(order.getCustomer());
			    	  ord.setProducts(order.getProducts());
			        return orderRepository.save(order);
			      })
			      .orElseGet(() -> {
			    	  order.setId(id);
			        return orderRepository.save(order);
			      });
	}

}
