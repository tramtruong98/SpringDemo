package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrderDetail;
import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;


@RestController
//@RequestMapping("api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public ResponseEntity<List<OrderDetail>> getAllBooks() {
		List<OrderDetail> orders = orderService.getListOrders();
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDetail> findOrderById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
	}

	@GetMapping("/count")
	public Long count() {
		return orderService.count();
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrderById(@PathVariable Long id) {
		orderService.deleteById(id);
		return ResponseEntity.ok("delete user successfully");
	}

	@PostMapping("/create")
	public ResponseEntity<Order> createOrder(@RequestBody OrderDetail order) {
		Order newOrder = orderService.addOrder(order);
		return ResponseEntity.ok().body(newOrder);
	}

//	@PutMapping("/update/{id}")
//	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id) {
//
//		Order editOrder = orderService.updateOrder(order, id);
//		return ResponseEntity.ok(editOrder);
//	}

}
