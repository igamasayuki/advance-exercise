package com.example.ec_201804d.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.OrderItem;

@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public void save(OrderItem orderItem) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "insert into order_items(item_id,order_id,quantity)values(:itemId,:orderId,:quantity)";
		template.update(sql, param);
	}

}
