package com.example.ec_201804d.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;

@Repository
public class OrderItemRepository {
	
	public List<Order> findByUserIdAndStatus(long userId, Integer status) {
		return null;
	}
	
	public void save(OrderItem orderItem) {
		
	}
	
}
