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

@Repository
public class AdminUserRepoistory {
	private static final RowMapper<AdminUser> AdminUserRowMapper=(rs,i)->{
		AdminUser registerAdminUser = new AdminUser();
		registerAdminUser.setName(rs.getString("name"));
		registerAdminUser.setEmail(rs.getString("email"));
		registerAdminUser.setPassword(rs.getString("password"));
		return registerAdminUser;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public void insert(AdminUser adminUser) {
		Integer currentMaxId =getMaxId();
		if (currentMaxId == null) {
			// テーブルにデータがない場合 -> IDを付与する
			currentMaxId = 1;
			adminUser.setId(currentMaxId);
		}else {
			adminUser.setId(currentMaxId+1);
		}
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUser);
		String sql = "insert into admin_users(id,name,email,password)values(:id,:name,:mailAddress,:password);";
		template.update(sql, param);
	}
	
	public AdminUser findByMailAddress(String mailAddress) {
		String sql="select id,name,email,password from admin_users where email=:mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		List<AdminUser> adminUserList = template.query(sql,param,AdminUserRowMapper);
		if(adminUserList.size() == 0) {
			return null;
		}
		return adminUserList.get(0);
	}
	
	public Integer getMaxId() {
		try {
			Integer maxId = template.queryForObject("SELECT MAX(id) FROM admin_users;", 
													new MapSqlParameterSource(),Integer.class);
			return maxId;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return null;
		}
	}
}
