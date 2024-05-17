package com.be.dao;

import java.util.List;

import com.be.entities.Result;

public interface IDao <T> {
	public List<T> getAll();
	public T getById(Integer id);
	public List<T> searchByName(String name);
	
	public Result insert(T t);
	public Result update(T t);
	public Result delete(Integer id);
}
