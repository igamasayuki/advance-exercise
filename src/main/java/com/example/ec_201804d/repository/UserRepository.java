package com.example.ec_201804d.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		user.setZipCode(rs.getString("zipCode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};

	public void registerUser(User user) {
		String sql = "insert into " + TABLE_NAME + " values(:name,:email,:password,:zipCode,:address,:telephone)";
		// BeanPropertySqlParameterSource()
	}
}
