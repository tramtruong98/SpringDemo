package com.example.demo.services;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.OrderDetail;
import com.example.demo.DTO.ProductDetail;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.CustomerRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	// EntityManagerFactory entityManagerFactory =
	// Persistence.createEntityManagerFactory("persistence");

	@Override
	public List<OrderDetail> getListOrders() {
		List<Order> listOrder = orderRepository.findAll();
		List<OrderDetail> listOrderDetail = new ArrayList<>();
		for (Order order : listOrder) {
			List<Product> listProduct = order.getProducts();
			List<ProductDetail> listProductDetail = new ArrayList<ProductDetail>();
			for (Product product : listProduct) {
				listProductDetail.add(new ProductDetail(product.getId(), product.getName()));
				listOrderDetail.add(new OrderDetail(order.getId(), order.getAmount(), order.getTotalPrice(),
						order.getCustomer().getId(), order.getCustomer().getName(), listProductDetail));
			}
		}

		return listOrderDetail;
	}

	@Override
	public OrderDetail getOrderById(Long id) {
		Order order = orderRepository.findById(id).get();
		List<Product> listProduct = order.getProducts();
		List<ProductDetail> listProductDetail = new ArrayList<ProductDetail>();
		for (Product product : listProduct) {
			listProductDetail.add(new ProductDetail(product.getId(), product.getName()));
		}
		OrderDetail orderDetail = new OrderDetail(order.getId(), order.getAmount(), order.getTotalPrice(),
				order.getCustomer().getId(), order.getCustomer().getName(), listProductDetail);
		return orderDetail;
	}

	@Override
	public void deleteById(Long id) {
		Order order = orderRepository.findById(id).get();
		order.getProducts().remove(order);
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

	@Override
	public Order updateOrder(OrderDetail orderDetail, Long id) {
		Order order = orderRepository.findById(id).get();
		List<ProductDetail> listProductDetail = orderDetail.getProductDetail();
		List<Product> listProduct = new ArrayList<>();
		for (ProductDetail productDetail : listProductDetail) {
			listProduct.add(productRepository.findById(productDetail.getId()).get());
		}
		order.setAmount(orderDetail.getAmount());
		order.setTotalPrice(orderDetail.getTotalPrice());
		order.setProducts(listProduct);
		Order newOrder = orderRepository.save(order);
		newOrder.getProducts().forEach(p -> {
			p.getOrders().add(order);
		});
		return newOrder;
	}

	@Override
	public Order addOrder(OrderDetail orderDetail) {
		Order order = new Order(orderDetail.getAmount(), orderDetail.getTotalPrice());
		List<ProductDetail> listProductDetail = orderDetail.getProductDetail();
		List<Product> listProduct = new ArrayList<>();
		for (ProductDetail productDetail : listProductDetail) {
			Product existedProduct = productRepository.findById(productDetail.getId()).get();
			listProduct.add(existedProduct);
		}
		Customer customer;
		if (customerRepository.existsById(orderDetail.getCustomerId())) {
			customer = customerRepository.findById(orderDetail.getCustomerId()).get();
		} else
			customer = new Customer(orderDetail.getCustomerName());
		order.setCustomer(customer);
		order.setProducts(listProduct);
		Order newOrder = orderRepository.save(order);
		newOrder.getProducts().forEach(p -> {
			p.getOrders().add(order);
		});
		return newOrder;
	}

	@Override
	public List<Product> getProduct(Long id) {
		Order order = orderRepository.findById(id).get();
		List<Product> products = order.getProducts();
		return products;
	}

}