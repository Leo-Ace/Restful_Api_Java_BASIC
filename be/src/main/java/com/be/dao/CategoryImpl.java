package com.be.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.be.entities.Category;
import com.be.entities.HibernateUtil;
import com.be.entities.Result;

public class CategoryImpl implements IDao<Category> {
	SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();

	@Override
	public List<Category> getAll() {
		Session session = sessionFactory.openSession();
		List<Category> data = new ArrayList<Category>();
		Query query = session.createQuery("from Category");
		data = query.getResultList();
		session.close();
		return data;
	}

	@Override
	public Category getById(Integer id) {
		Session session = sessionFactory.openSession();
		Category category = session.get(Category.class, id);
		session.close();
		return category;
	}

	@Override
	public List<Category> searchByName(String name) {
		Session session = sessionFactory.openSession();
		List<Category> data = new ArrayList<Category>();
		Query query = session.createQuery("from Category where name like ?");
		query.setParameter(1, "%" + name + "%");
		data = query.getResultList();
		session.close();
		return data;
	}

	@Override
	public Result insert(Category t) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Result result = null;
		try {
			session.save(t);
			result = new Result(200, "Insert Successfully!");
		} catch (Exception e) {
			result = new Result(500, "Insert Unsuccessful!");			
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@Override
	public Result update(Category t) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Result result = null;
		try {
			session.update(t);
			result = new Result(200, "Update Successfully!");
		} catch (Exception e) {
			result = new Result(500, "Update Unsuccessful!");			
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@Override
	public Result delete(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Result result = null;
		try {
			Category category = session.get(Category.class, id);
			session.remove(category);
			result = new Result(200, "Delete Successfully!");
		} catch (Exception e) {
			result = new Result(500, "Delete Unsuccessful!");			
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
}
