package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.DTO.OrderDetail;
import com.example.demo.DTO.ProductDetail;
import com.example.demo.controllers.OrderController;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private OrderService orderService;

	@Test
	public void testGetAllOrder() {
		List<OrderDetail> orders = new ArrayList<>();
		Customer customer = new Customer("tram");
		List<ProductDetail> products = new ArrayList<>();
		products.add(new ProductDetail(1l, "shirt"));
		products.add(new ProductDetail(2l, "shirt"));
		orders.add(new OrderDetail(1l, 2l, 200l, customer.getId(), customer.getName(), products));
		orders.add(new OrderDetail(2l, 2l, 300l, customer.getId(), customer.getName(), products));
		Mockito.when(orderService.getListOrders()).thenReturn(orders);
		String url = "/api/orders";
		try {
			MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
			String actualJsonResponse = result.getResponse().getContentAsString();
			System.out.println(actualJsonResponse);
			String expectedJsonResult = objectMapper.writeValueAsString(orders);
			assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateOrder() throws Exception {
		Customer customer = new Customer("tram");
		List<ProductDetail> productDetails = new ArrayList<>();
		productDetails.add(new ProductDetail(1l, "shirt"));
		productDetails.add(new ProductDetail(2l, "skirt"));
		List<Product> products = new ArrayList<>();
		products.add(new Product(1l, "shirt", 20l, 100l));
		products.add(new Product(2l, "skirt", 20l, 200l));
		OrderDetail orderDetail = new OrderDetail(1l, 2l, 200l, customer.getId(), customer.getName(), productDetails);
		Order order = new Order(2l, 200l);
		order.setCustomer(customer);
		order.setProducts(products);
		Mockito.when(orderService.addOrder(orderDetail)).thenReturn(order);
		String url = "/api/orders/create";
		MvcResult result = mockMvc.perform(
				post(url).contentType("application/json").content(objectMapper.writeValueAsString(order)).with(csrf()))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(content, "create order successfully");

	}

	@Test
	public void testUpdateOrder() throws JsonProcessingException, Exception {
		Long id = 1l;
		Customer customer = new Customer("tram");
		List<ProductDetail> productDetails = new ArrayList<>();
		productDetails.add(new ProductDetail(1l, "shirt"));
		productDetails.add(new ProductDetail(2l, "skirt"));
		List<Product> products = new ArrayList<>();
		products.add(new Product(1l, "shirt", 20l, 100l));
		products.add(new Product(2l, "skirt", 20l, 200l));
		OrderDetail orderDetail = new OrderDetail(1l, 2l, 300l, customer.getId(), customer.getName(), productDetails);
		Order order = new Order(2l, 200l);
		order.setCustomer(customer);
		order.setProducts(products);
		Mockito.when(orderService.updateOrder(orderDetail, id)).thenReturn(order);
		String url = "/api/orders/update/" + id;
		MvcResult result = mockMvc.perform(
				put(url).contentType("application/json").content(objectMapper.writeValueAsString(order)).with(csrf()))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(content, "update order successfully");
	}

	@Test
	public void testDelete() throws Exception {
		Long id = 1l;
		Mockito.doNothing().when(orderService).deleteById(id);
		String url = "/order/" + id;
		mockMvc.perform(delete(url)).andExpect(status().isOk());

	}

	@Test
	public void testGetOrderById() throws Exception {
		Long id = 1l;
		Customer customer = new Customer("tram");
		List<ProductDetail> products = new ArrayList<>();
		products.add(new ProductDetail(1l, "shirt"));
		products.add(new ProductDetail(2l, "shirt"));
		OrderDetail order = new OrderDetail(1l, 2l, 200l, customer.getId(), customer.getName(), products);
		Mockito.when(orderService.getOrderById(id)).thenReturn(order);
		String url = "/api/orders/" + id;
		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = result.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		String expectedJsonResult = objectMapper.writeValueAsString(order);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResult);

	}
}
