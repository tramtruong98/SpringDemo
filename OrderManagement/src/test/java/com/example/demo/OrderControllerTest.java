package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controllers.OrderController;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;
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
		List<Order> orders = new ArrayList<>();
		Customer customer = new Customer("tram");
		orders.add(new Order(1l, 2l, 200l, customer, null));
		orders.add(new Order(1l, 2l, 300l, customer, null));
		Mockito.when(orderService.getListOrder()).thenReturn(orders);
		String url = "/orders";
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
//		Order newOrder = new Order(1l, 2l, 200l, customer, null);
//		Order savedOrder = new Order(1l, 2l, 200l, customer, null);
//		Mockito.when(orderService.createOrder(newOrder)).thenReturn(savedOrder);
//		String url = "/create";
//		//String expectedJsonResult = objectMapper.writeValueAsString(savedOrder);
//		mockMvc.perform(post(url).contentType("application/json").content(objectMapper.writeValueAsString(newOrder)).with(csrf()))
//				.andExpect(status().isOk());
//		//assertThat(objectMapper.writeValueAsString(newOrder)).isEqualToIgnoringWhitespace(expectedJsonResult);
		   String url = "/create";
		   Order newOrder = new Order(1l, 2l, 200l, customer, null);
		   
		   String inputJson = objectMapper.writeValueAsString(newOrder);
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(content, "create successfully");

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
		Customer customer = new Customer("tram");
		Order order = new Order(1l, 2l, 200l, customer, null);
		Long id = 1l;
		Mockito.when(orderService.getOrderById(id)).thenReturn(order);
		String url = "/order/" + id;
		mockMvc.perform(delete(url)).andExpect(status().isOk());
		
	}
}
