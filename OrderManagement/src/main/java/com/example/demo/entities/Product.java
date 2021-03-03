package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long number;
	private Long price;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "product_order", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
	private List<Order> orders;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(Long id, String name, Long number, Long price) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.price = price;
	}
	
}
