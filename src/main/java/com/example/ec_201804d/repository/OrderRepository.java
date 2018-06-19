package com.example.ec_201804d.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.Order;


/**
 * 注文一覧を表示するレポジトリ.
 * 
 * @author takanori.noguchi
 *
 */
@Repository
public class OrderRepository {
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER =(rs,i) ->{
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setOrderNumber(rs.getString("order_number"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setUserId(rs.getInt("user_id"));
		return order;
	};
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	/**
	 * 全件検索を行うメソッド.
	 * 
	 * @return 注文のリストを返す
	 */
	public List<Order> findAll(){
		String sql ="SELECT id,order_number,user_id,status,total_price FROM orders";
		
		List<Order> orderList = template.query(sql, ORDER_ROW_MAPPER);
		return orderList;
	}
}
