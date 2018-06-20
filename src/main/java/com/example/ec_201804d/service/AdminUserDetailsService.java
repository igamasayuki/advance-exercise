package com.example.ec_201804d.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.domain.LoginAdminUser;
import com.example.ec_201804d.repository.AdminUserRepository;

/**
 * ログイン後の管理者情報に権限情報を付与するサービスクラス.
 * 
 * @author hibiki.ono
 *
 */
@Service
public class AdminUserDetailsService {

	@Autowired
	private AdminUserRepository adminUserRepository;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		AdminUser adminUser = adminUserRepository.findByMailAddress(email);
		if(adminUser == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return new LoginAdminUser(adminUser, authorityList);
	}
}
