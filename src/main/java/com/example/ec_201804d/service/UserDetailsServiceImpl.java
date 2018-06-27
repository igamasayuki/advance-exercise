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
import com.example.ec_201804d.domain.OrderItem;
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

	/** セッションスコープ */
	@Autowired
	HttpSession session;
	/** ユーザDBを操作するリポジトリ */
	@Autowired
	private UserRepository userRepository;
	/** 注文DBを操作するリポジトリ */
	@Autowired
	private OrderRepository orderRepository;
	/** 注文商品DBを操作するリポジトリ */
	@Autowired
	OrderItemRepository orderItemRepository;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAddress(email);
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		
		if(user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		updateOrder(user);
		
		return new LoginUser(user,authorityList);
	}
	
	/**
	 * 注文情報を更新する.
	 * ログアウト状態でショッピングカートに商品が存在しログインした場合に、注文情報を更新する。
	 * @param user ログインしたユーザの情報
	 */
	private void updateOrder(User user) {
		long preUserId = session.getId().hashCode();
		long userId = user.getId();
		List<Order> orderList = orderRepository.findByUserIdAndStatus(userId, 0);
		if(!orderList.isEmpty()) {
			Order order = orderList.get(0);
			List<Order> preOrderList =  orderRepository.findByUserIdAndStatus(preUserId, 0);
			if(!preOrderList.isEmpty()) {
				Order preOrder = preOrderList.get(0);
				orderItemRepository.updateOrderId(preOrder.getId(), order.getId());
				for (OrderItem orderItem : order.getOrderItemList()) {
					System.out.println("orderItem.getOrderId:" + orderItem.getOrderId());
					orderItemRepository.uniteSameItemOfOrder(orderItem);
				}
				orderRepository.deleteByUserId(preUserId);
			}
			
		}
		orderRepository.updateUserId(preUserId, userId);
	}
}
