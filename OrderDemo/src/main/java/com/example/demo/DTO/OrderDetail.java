package com.example.demo.DTO;

public class OrderDetail {
	private Long id;
	private Long amount;
	private Long totalPrice;
	private Long customerId;
	private String customerName;
	public OrderDetail() {
		
	}
	public OrderDetail(Long id, Long amount, Long totalPrice, Long customerId, String customerName) {
		this.id = id;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
		this.customerName = customerName;
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
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String productName) {
		this.customerName = productName;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

}
