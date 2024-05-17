package com.fe.entities;

import java.util.List;

public class Paginate <T> {
	private List<T> data;
	private int totalPages;
	private int pageSize;
	private int currentPage;
	
	public Paginate() {
		// TODO Auto-generated constructor stub
	}

	public Paginate(List<T> data, int totalPages, int pageSize, int currentPage) {
		super();
		this.data = data;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
