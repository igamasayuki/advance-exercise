package com.example.ec_201804d.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_201804d.domain.Item;

/**
 * 商品情報を操作するメソッド.
 * 
 * @author minori.matsuoka
 *
 */
@Repository
public class ItemRepository {

	private static final String TABLE_NAME = "items";
	private static final RowMapper<Item> ITEM_ROW_MAPPER =(rs,i) ->{
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getInt("price"));
		item.setImagePath(rs.getString("imagepath"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	/**
	 * 全件検索を行うメソッド.
	 * 
	 * @return 商品情報のリストを返します
	 */
	public List<Item> findAll(){
		String sql ="SELECT id,name,description,price,imagepath,deleted FROM " + TABLE_NAME + " WHERE deleted IS FALSE";
		
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品検索を行うメソッド.
	 * @param word 入力された単語
	 * @return　該当する商品情報を返します
	 */
	public List<Item> findByWord(String word){
		String sql="SELECT id,name,description,price,imagepath,deleted FROM items WHERE name LIKE :word AND deleted IS FALSE";
	
		SqlParameterSource param = new MapSqlParameterSource().addValue("word","%" +  word + "%");
		
		List<Item> findItemList = template.query(sql, param,ITEM_ROW_MAPPER);
		
		return findItemList;
	
	}
	
	public void insert(Item item) {
		String insertSql = "INSERT INTO " + TABLE_NAME + "(name, description, price, imagepath, deleted)"
				+ "VALUES(:name, :description, :price, :imagePath, :deleted)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(insertSql, param);
	}
}