package com.example.ec_201804d.domain;

/**
 * @author kakiki Itemを表すクラス.
 */
public class Item {
	/** id */
	private long id;
	/** 名前 */
	private String name;
	/** 説明 */
	private String description;
	/** 値段 */
	private Integer price;
	/** 画像URL */
	private String imagePath;
	/** 削除フラグ */
	private boolean deleted;

	public Item() {
	}

	public Item(String name, String description, Integer price, String imagePath, Boolean deleted) {
		setName(name);
		setDescription(description);
		setPrice(price);
		setImagePath(imagePath);
		setDeleted(deleted);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
