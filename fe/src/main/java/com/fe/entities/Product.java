package com.fe.entities;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Integer category_id;
	private String name;
	private Float price;
	private String description;
	private String thumbnail;
	private Date created_at;
	private Category category;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, Integer category_id, String name, Float price, String description, String thumbnail,
			Date created_at) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.thumbnail = thumbnail;
		this.created_at = created_at;
	}
	
	public Product(Integer category_id, String name, Float price, String description, String thumbnail,
			Date created_at) {
		super();
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.thumbnail = thumbnail;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
