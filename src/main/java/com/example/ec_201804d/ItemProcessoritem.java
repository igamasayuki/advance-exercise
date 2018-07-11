package com.example.ec_201804d;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.example.ec_201804d.domain.Item;

/**
 * 登録商品情報を加工するコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Configuration
@EnableBatchProcessing
public class ItemProcessoritem implements ItemProcessor<Item, Item> {

	@Override
	public Item process(Item item) throws Exception {
		final String name = item.getName();
		final String description = item.getDescription();
		final Integer price = item.getPrice();
		final String imagePath = item.getImagePath();
		final Boolean deleted = item.isDeleted();

		final Item transformColumns = new Item(name, description, price, imagePath, deleted);
		
		return transformColumns;
	}

}
