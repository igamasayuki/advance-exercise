package com.example.ec_201804d.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;

@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private final String TABLE_NAME = "order_items";

	private static final ResultSetExtractor<List<Order>> ORDER_EXTRACTOR = (rs) -> {
		List<Order> orderList = new ArrayList<>();
		Order order = null;
		List<OrderItem> orderItemList = null;
		long beforeOrderId = 0;
		while (rs.next()) {
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
			item.setId(rs.getLong("item_id"));
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

	public void save(OrderItem orderItem) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "insert into " + TABLE_NAME + "(item_id,order_id,quantity)values(:itemId,:orderId,:quantity)";
		template.update(sql, param);
	}

	public void updateOrderId(Long from, Long to) {
		String UpdateSql = "UPDATE order_items SET order_id=:to WHERE order_id=:from";
		SqlParameterSource param = new MapSqlParameterSource().addValue("to", to).addValue("from", from);
		template.update(UpdateSql, param);
	}

	public void deleteById(long id) {
		String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);
	}

}
