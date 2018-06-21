package com.example.ec_201804d.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.repository.UserRepository;

/**
 * ログイン後のユーザ情報に権限情報を付与するサービスクラス.
 * 
 * @author hibiki.ono
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAddress(email);
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		
		if(user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new LoginUser(user,authorityList);
	}
}
