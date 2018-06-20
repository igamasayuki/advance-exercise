package com.example.ec_201804d.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.User;

/**
 * @author hibiki.ono
 *
 *         ユーザ登録情報を扱うレポジトリクラス.
 */
@Repository
public class UserRepository {

	private String TABLE_NAME = "users";

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<User> userRowMapper = (rs, i) -> {
		User user = new User();
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipCode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};

	/**
	 * ユーザ登録.
	 * 
	 * @param user
	 *            登録情報
	 */
	public void registerUser(User user) {
		String sql = "insert into " + TABLE_NAME
				+ "(name,email,password,zipcode,address,telephone) values(:name,:email,:password,:zipCode,:address,:telephone)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}

	/**
	 * メールアドレス検索.
	 * 
	 * @param address
	 *            メールアドレス
	 * @return ユーザ情報
	 */
	public User findByEmailAddress(String address) {
		User user = new User();
		try {
			String sql = "select name,email,password,zipcode,address,telephone from " + TABLE_NAME
					+ " where email=:address";
			SqlParameterSource param = new MapSqlParameterSource().addValue("address", address);
			user = template.queryForObject(sql, param, userRowMapper);
			System.out.println(user.getName());
		} catch (EmptyResultDataAccessException ee) {
			ee.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		return user;
	}
}
