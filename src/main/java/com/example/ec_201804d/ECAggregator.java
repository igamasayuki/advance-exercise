package com.example.ec_201804d;

import org.springframework.batch.item.file.transform.LineAggregator;

import com.example.ec_201804d.domain.Order;

/**
 * DBの注文情報をcsvファイルに書き込むコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
public class ECAggregator implements LineAggregator<Order>{

	@Override
	public String aggregate(Order order) {
		StringBuilder sb = new StringBuilder();
		System.out.println(order.getId());
		sb.append(order.getId());
		sb.append(",");
		sb.append(order.getOrderNumber());
		sb.append(",");
		sb.append(order.getUserId());
		sb.append(",");
		sb.append(order.getStatus());
		sb.append(",");
		sb.append(order.getTotalPrice());
		sb.append(",");
		sb.append(order.getOrderDate());
		sb.append(",");
		sb.append(order.getDeliveryName());
		sb.append(",");
		sb.append(order.getDeliveryEmail());
		sb.append(",");
		sb.append(order.getDeliveryZipCode());
		sb.append(",");
		sb.append(order.getDeliveryAddress());
		sb.append(",");
		sb.append(order.getDeliveryTel());
		
		return sb.toString();
	}
	
	

}
