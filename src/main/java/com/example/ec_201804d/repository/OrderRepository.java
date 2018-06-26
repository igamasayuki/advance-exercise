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

import com.example.ec_201804d.domain.Info;
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
	
	/** 対象テーブル名 */
	private String TABLE_NAME = "orders";

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

	private static final RowMapper<Info> InfoRowMapper = (rs, i) -> {
		Info info = new Info();
		info.setName(rs.getString("iName"));
		info.setPrice(rs.getInt("iPrice"));
		info.setQuantity(rs.getInt("oiQuantity"));
		info.setTotalPrice(rs.getInt("oTotal"));

		return info;
	};

	@Autowired
	NamedParameterJdbcTemplate template;

	/**
	 * 全件検索を行うメソッド.
	 * 
	 * @return 注文のリストを返す
	 */
	public List<Order> findAll() {
		String sql = "SELECT id," + "order_number," + "user_id,status," + "total_price,"
				+ "order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel "
				+ "FROM " + TABLE_NAME + " ORDER BY id";
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

	public Order load(long id) {
		String sql = "SELECT id,order_number,user_id,status,total_price,order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel FROM " + TABLE_NAME + " WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}

	/**
	 * 新規注文を登録する.
	 * @param order 注文情報
	 */
	public void insertNewOrder(Order order) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "insert into " + TABLE_NAME + "(order_number,user_id,status,total_price,order_date)values(:orderNumber,:userId,:status,:totalPrice,:orderDate);";
		template.update(sql, param);
	}

	/**
	 * @param orderId
	 *            オーダーID
	 * @return itemから取得しリストに返す
	 */
	public List<Info> find(long orderId) {
		  String sql="select i.price as iPrice,i.name as iName,oi.quantity as oiQuantity,o.total_price as oTotal "
		  		+ "from " + TABLE_NAME + " as o inner join order_items as oi on(o.id=oi.order_id)" + 
		    "inner join items as i on(oi.item_id=i.id) where oi.order_id=:orderId";
		  SqlParameterSource param = new MapSqlParameterSource().addValue("orderId",orderId);
		  List<Info>list=template.query(sql,param,InfoRowMapper);
		  return list;
		 }


	/**
	 * ユーザIDとステータスから検索を行う.
	 * 
	 * @param userId
	 *            ユーザID
	 * @param status
	 *            ステータス (0:購入前,1:未入金,2:入金済,3:発送済,9:キャンセル)
	 * @return 該当するオーダーの一覧
	 */
	public List<Order> findByUserIdAndStatus(long userId, Integer status) {
		String findSql = "SELECT o.id AS ID, order_number, user_id, status, "
				+ "oi.id AS orderitem_id, oi.item_id AS item_id, i.name AS item_name, description, price, imagepath, deleted, quantity, total_price, order_date, "
				+ "delivery_name, delivery_email, delivery_zip_code, delivery_address, delivery_tel "
				+ "FROM " + TABLE_NAME +" o LEFT OUTER JOIN order_items oi ON o.id = oi.order_id LEFT OUTER JOIN items i ON oi.item_id = i.id "
				+ "WHERE user_id=:userId AND status=:status";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orders = template.query(findSql, param, ORDER_EXTRACTOR);
		return orders;
	}

	/**
	 * オーダー情報を更新する.
	 * 
	 * @param order
	 *            更新するオーダー情報
	 */
	public void update(Order order) {
		String updateSql = "UPDATE " + TABLE_NAME + " SET"
				+ " order_number=:orderNumber, user_id=:userId, status=:status, total_price=:totalPrice, order_date=:orderDate, delivery_name=:deliveryName, delivery_email=:deliveryEmail, delivery_zip_code=:deliveryZipCode, delivery_address=:deliveryAddress, delivery_tel=:deliveryTel"
				+ " WHERE id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(updateSql, param);
	}

	
	

	/**
	 * ユーザIDを変更する.
	 * @param preUserId 変更前のユーザID
	 * @param userId 変更後のユーザID
	 */
	public void updateUserId(long preUserId, long userId) {
		String updateSql = "UPDATE " + TABLE_NAME + " SET user_id=:userId WHERE user_id=:preUserId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("preUserId", preUserId);
		template.update(updateSql, param);
	}
	
	/**
	 * 引数で与えられたユーザIDの注文を削除する.
	 * @param userId ユーザID
	 */
	public void deleteByUserId(long userId) {
		String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE user_id=:userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		template.update(deleteSql, param);
	}
	

	/**
	 * 注文ステータスを変更する.
	 * 
	 * @param status 注文状況
	 * @param id　注文状況を変更するid
	 */
	public void updateStatus(int status, Long id) {

		SqlParameterSource param = new MapSqlParameterSource().addValue("status", status).addValue("id", id);
		String sql = "update " + TABLE_NAME + " set status=:status where id=:id";

		template.update(sql, param);
	}

}
