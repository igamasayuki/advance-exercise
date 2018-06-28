package com.example.ec_201804d;

import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 複数のアプリを１つのサーバ上で動かす際に必要な設定.
 * 
 * @author igamasayuki
 *
 */
public class HikariDataSouseConfig  extends HikariConfig {

	/**
	 * 複数のアプリを１つのサーバ上で動かす際に必要な設定.
	 * @return データソース設定
	 * @throws SQLException データベース関連の不具合が生じたときに発生する例外
	 */
	@Bean
	public DataSource dataSource() throws SQLException {
	    HikariDataSource dataSource = new HikariDataSource(this);
	    dataSource.setPoolName("dataSource_" + UUID.randomUUID().toString());
	    return dataSource;
	}
    
}
