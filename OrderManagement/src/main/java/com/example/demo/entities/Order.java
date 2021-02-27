package com.example.demo.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long amount;
	private Long totalPrice;
	@ManyToOne()
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	@ManyToMany(mappedBy = "orders")
	private List<Product> products;

}
