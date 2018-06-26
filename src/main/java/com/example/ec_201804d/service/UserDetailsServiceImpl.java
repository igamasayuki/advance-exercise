package com.example.ec_201804d.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.repository.OrderItemRepository;
import com.example.ec_201804d.repository.OrderRepository;
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
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	HttpSession session;
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAddress(email);
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		
		if(user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		long preUserId = session.getId().hashCode();
		long userId = user.getId();
		
		List<Order> orderList = orderRepository.findByUserIdAndStatus(userId, 0);
		if(!orderList.isEmpty()) {
			long orderId = orderList.get(0).getId();
			List<Order> preOrderList =  orderRepository.findByUserIdAndStatus(preUserId, 0);
			if(!preOrderList.isEmpty()) {
				long preOrderId = preOrderList.get(0).getId();
				orderItemRepository.updateOrderId(preOrderId, orderId);
				orderRepository.deleteByUserId(preUserId);
			}
			
		}
		orderRepository.updateUserId(preUserId, userId);
		
		
		return new LoginUser(user,authorityList);
	}
}
