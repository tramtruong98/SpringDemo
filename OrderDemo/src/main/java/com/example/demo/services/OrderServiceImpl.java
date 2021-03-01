package com.example.demo.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDetail;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.repositories.CRUDRepository;
import com.example.demo.repositories.CustomerRepository;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private CRUDRepository orderRepository;
	@Autowired
    private CustomerRepository customerRepository;
	
	@Override
	public List<OrderDetail> getListOrders() {
		//List<Order> orders = new ArrayList<>(); 
		//orderRepository.findAll().forEach(order -> orders.add(order));
		List<Order> listOrder = orderRepository.findAll();
		List<OrderDetail> listOrderDetail = new ArrayList<>();
		for(Order order: listOrder) {
		listOrderDetail.add(new OrderDetail(order.getId(), order.getAmount(), order.getTotalPrice(), order.getCustomer().getId(), order.getCustomer().getName()));		
		}
		
		return listOrderDetail;
	}

	@Override
	public OrderDetail getOrderById(Long id) {
		Order order = orderRepository.findById(id).get();
		OrderDetail orderDetail = new OrderDetail(order.getId(), order.getAmount(), order.getTotalPrice(), order.getCustomer().getId(), order.getCustomer().getName());
		return orderDetail;
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}


//	@Override
//	public OrderDetail searchOrder(String keyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Long count() {
		return orderRepository.count();
	}



//	@Override
//	public OrderDetail updateOrder(OrderDetail order, Long id) {
//		  orderRepository.findById(id);
//			        return orderRepository.save(order);
//	}

	@Override
	public Order addOrder(OrderDetail order) {
		if (customerRepository.existsById(order.getCustomerId()))
		{
			Customer customer = customerRepository.findById(order.getCustomerId()).get();
			return orderRepository.save(new Order(order.getAmount(), order.getTotalPrice(), customer));
		}
		Customer customer = new Customer(order.getCustomerName());
		return orderRepository.save(new Order(order.getAmount(), order.getTotalPrice(), customer));
	}

}
