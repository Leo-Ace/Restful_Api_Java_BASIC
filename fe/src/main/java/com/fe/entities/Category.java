package com.fe.entities;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Integer status;
	private Date created_at;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int id, String name, Integer status, Date created_at) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
