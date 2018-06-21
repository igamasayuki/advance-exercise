package com.example.ec_201804d.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;

/**
 * 注文一覧を表示するレポジトリ.
 * 
 * @author takanori.noguchi
 *
 */
@Repository
public class OrderRepository {

	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setOrderNumber(rs.getString("order_number"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDeliveryName(rs.getString("delivery_name"));
		order.setDeliveryEmail(rs.getString("delivery_email"));
		order.setDeliveryZipCode(rs.getString("delivery_zip_code"));
		order.setDeliveryAddress(rs.getString("delivery_address"));
		order.setDeliveryTel(rs.getString("delivery_tel"));

		return order;
	};
	
	private static final ResultSetExtractor<List<Order>> ORDER_EXTRACTOR = (rs) -> {
		List<Order> orderList = new ArrayList<>();
		Order order = null;
		List<OrderItem> orderItemList = null;
		long beforeOrderId = 0;
		while(rs.next()) {
			if (rs.getInt("ID") != beforeOrderId) {
				order = new Order();
				order.setId(rs.getLong("ID"));
				order.setOrderNumber(rs.getString("order_number"));
				order.setUserId(rs.getLong("user_id"));
				order.setStatus(rs.getInt("status"));
				orderItemList = new LinkedList<>();
				order.setOrderItemList(orderItemList);
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDeliveryName(rs.getString("delivery_name"));
				order.setDeliveryEmail(rs.getString("delivery_email"));
				order.setDeliveryZipCode(rs.getString("delivery_zip_code"));
				order.setDeliveryAddress(rs.getString("delivery_address"));
				order.setDeliveryTel(rs.getString("delivery_tel"));
				orderList.add(order);
			}
			OrderItem orderItem = new OrderItem();
			orderItem.setId(rs.getLong("orderitem_id"));
			Item item = new Item();
			item.setName(rs.getString("item_name"));
			item.setDescription(rs.getString("description"));
			item.setPrice(rs.getInt("price"));
			item.setImagePath(rs.getString("imagepath"));
			item.setDeleted(rs.getBoolean("deleted"));
			orderItem.setItem(item);
			orderItem.setQuantity(rs.getInt("quantity"));
			orderItemList.add(orderItem);

			beforeOrderId = order.getId();
		}
		return orderList;
	};
	
	@Autowired
	NamedParameterJdbcTemplate template;

	/**
	 * 全件検索を行うメソッド.
	 * 
	 * @return 注文のリストを返す
	 */
	public List<Order> findAll() {
		String sql = "SELECT id,order_number,user_id,status,total_price,order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel FROM orders ORDER BY id";
		List<Order> orderList = template.query(sql, ORDER_ROW_MAPPER);
		return orderList;
	}

	/**
	 * 注文詳細表示メソッド.
	 * 
	 * @param id
	 *            ID
	 * @return 注文情報を返す
	 */
	public Order load(Integer id) {
		String sql = "SELECT id,order_number,user_id,status,total_price,order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel FROM orders WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}

	public List<Order> findByUserIdAndStatus(long userId, Integer status) {
		String findSql = "SELECT o.id AS ID, order_number, user_id, status, "
				+ "oi.id AS orderitem_id, i.name AS item_name, description, price, imagepath, deleted, quantity, total_price, order_date, "
				+ "delivery_name, delivery_email, delivery_zip_code, delivery_address, delivery_tel "
				+ "FROM orders o JOIN order_items oi ON o.id = oi.order_id JOIN items i ON oi.item_id = i.id "
				+ "WHERE user_id=:userId AND status=:status";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orders = template.query(findSql, param, ORDER_EXTRACTOR);
		return orders;
	}

	public void update(Order order) {
		String updateSql = "UPDATE orders SET"
				+ " total_price=:totalPrice, delivery_name=:deliveryName, delivery_email=:deliveryEmail, delivery_zip_code=:deliveryZipCode, delivery_address=:deliveryAddress, delivery_tel=:deliveryTel"
				+ " WHERE id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(updateSql, param);
	}
}
