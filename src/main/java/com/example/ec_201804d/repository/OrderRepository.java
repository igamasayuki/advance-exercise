package com.example.ec_201804d.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.domain.Order;

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
	
	public void insertNewOrder(Order order) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "insert into orders(order_number,user_id,status,total_price,order_date)values(:order_number,:user_id,:status,:total_price,:order_date);";
		template.update(sql, param);
	}

	public void insert(AdminUser adminUser) {
		Integer currentMaxId = getMaxId();
		if (currentMaxId == null) {
			// テーブルにデータがない場合 -> IDを付与する
			currentMaxId = 1;
			adminUser.setId(currentMaxId);
		} else {
			adminUser.setId(currentMaxId + 1);
		}
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUser);
		String sql = "insert into admin_users(id,name,email,password)values(:id,:name,:email,:password);";
		template.update(sql, param);
	}

	public Integer getMaxId() {
		try {
			Integer maxId = template.queryForObject("SELECT MAX(id) FROM admin_users;", new MapSqlParameterSource(),
					Integer.class);
			return maxId;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return null;
		}
	}
	
	public List<Order> findByUserIdAndStatus(long userId, Integer status) {
		
		String sql = "SELECT id,order_number,user_id,status,total_price,order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel FROM orders WHERE user_id=:userId AND status=:status ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orders = template.query(sql, param, ORDER_ROW_MAPPER);
		return orders;
	}
	
}
