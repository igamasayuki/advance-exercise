package com.example.ec_201804d.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;

/**
 * 注文商品DBを操作するリポジトリ.
 *
 * @author daiki.fujioka
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	/** 対象テーブル名(注文商品DB) */
	private final String TABLE_NAME = "order_items";
	private final RowMapper<OrderItem> OrderItemRowMapper = (rs, i) -> {
		OrderItem oi = new OrderItem();
		oi.setId(rs.getLong("id"));
		oi.setItemId(rs.getLong("item_id"));
		oi.setOrderId(rs.getLong("order_id"));
		oi.setQuantity(rs.getInt("quantity"));
		return oi;
	};

	/**
	 * 注文商品情報を追加する.
	 * すでに同注文に同じ注文商品が存在する場合は量が加算される。
	 * @param orderItem 新たに注文商品に追加する情報
	 */
	public void save(OrderItem orderItem) {
		OrderItem target = findByOrderIdAndItemId(orderItem.getOrderId(), orderItem.getItemId());
		if (target == null) {
			String sql = "insert into " + TABLE_NAME + "(item_id,order_id,quantity)values(:itemId,:orderId,:quantity)";
			SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
			template.update(sql, param);			
		} else {
			String updateSql = "UPDATE " + TABLE_NAME + " SET quantity=quantity+:quantity WHERE id=:id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("quantity", orderItem.getQuantity()).addValue("id", target.getId());
			template.update(updateSql, param);
		}
	}
	
	/**
	 * 注文IDを変更する.
	 * @param from 変更前の注文ID
	 * @param to 変更後の注文ID
	 */
	public void updateOrderId(long from, long to) {
		String UpdateSql = "UPDATE " + TABLE_NAME + " SET order_id=:to WHERE order_id=:from";
		SqlParameterSource param = new MapSqlParameterSource().addValue("to", to).addValue("from", from);
		template.update(UpdateSql, param);
	}

	/**
	 * 注文商品情報を削除する.
	 * @param id ID
	 */
	public void deleteById(long id) {
		String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);
	}
	
	/**
	 * 注文IDと商品IDから注文商品を検索する.
	 * @param itemId 商品ID
	 * @return　該当注文商品
	 */
	public OrderItem findByOrderIdAndItemId(long orderId, long itemId) {
		String countSql = "SELECT id, item_id, order_id, quantity FROM " + TABLE_NAME + " WHERE item_id=:itemId AND order_id=:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId).addValue("orderId", orderId);			
		OrderItem oi;
		try {
			oi = template.queryForObject(countSql, param, OrderItemRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return oi;
	}

	/**
	 * 同一の注文商品を結合する.
	 * 同一の注文商品の注文数を合計する。
	 * 1つを残して同じ情報を持つデータを削除する。
	 * @param orderItem 注文商品情報
	 */
	public void uniteSameItemOfOrder(OrderItem orderItem) {
		System.out.println("orderId:" + orderItem.getOrderId() + ", itemId:" + orderItem.getItemId());
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderItem.getOrderId()).addValue("itemId", orderItem.getItemId());
		String updateSql = "UPDATE " + TABLE_NAME + " SET quantity=(SELECT SUM(quantity) FROM " + TABLE_NAME + " WHERE order_id=:orderId AND item_id=:itemId) WHERE order_id=:orderId AND item_id=:itemId";
//		template.update(updateSql, param);
		int up = template.update(updateSql, param);
		System.out.println("up:" + up);
		String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE id<>(SELECT MIN(id) FROM " + TABLE_NAME + " WHERE order_id=:orderId AND item_id=:itemId) AND order_id=:orderId AND item_id=:itemId";
//		template.update(deleteSql, param);		
		int del = template.update(deleteSql, param);		
		System.out.println("del:" + del);
	}

}
