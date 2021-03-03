package com.example.demo.DTO;

import java.util.List;

import com.example.demo.entities.Product;

public class OrderDetail {
	private Long id;
	private Long amount;
	private Long totalPrice;
	private Long customerId;
	private String customerName;
	private List<ProductDetail> productDetail;

	public OrderDetail() {

	}

	public OrderDetail(Long id, Long amount, Long totalPrice, Long customerId, String customerName,
			List<ProductDetail> productDetail) {
		this.id = id;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
		this.customerName = customerName;
		this.productDetail = productDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<ProductDetail> getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(List<ProductDetail> productDetail) {
		this.productDetail = productDetail;
	}

	

}