package com.be.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.be.entities.Product;
import com.be.entities.HibernateUtil;
import com.be.entities.Paginate;
import com.be.entities.Product;
import com.be.entities.Result;

public class ProductImpl implements IDao<Product> {
	SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();

	@Override
	public List<Product> getAll() {
		Session session = sessionFactory.openSession();
		List<Product> data = new ArrayList<Product>();
		Query query = session.createQuery("from Product");
		data = query.getResultList();
		session.close();
		return data;
	}

	@Override
	public Product getById(Integer id) {
		Session session = sessionFactory.openSession();
		Product product = session.get(Product.class, id);
		session.close();
		return product;
	}
	
	@Override
	public List<Product> searchByName(String name) {
		Session session = sessionFactory.openSession();
		List<Product> data = new ArrayList<Product>();
		Query query = session.createQuery("from Product where name like ?");
		query.setParameter(1, "%" + name + "%");
		data = query.getResultList();
		session.close();
		return data;
	}
	
	public Paginate<Product> pagination(int page, int size, String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "from Product ";
		if(name != null) sql += "where name like "+"'%"+ name + "%'";
		Query query = session.createQuery(sql);
		int totalPage = (int) Math.ceil((double)query.getResultList().size()/size);
		List<Product> data = query.setFirstResult((page-1)*size).setMaxResults(size).getResultList();
		Paginate<Product> paginate = new Paginate<Product>(data, totalPage, size, page);
		session.getTransaction().commit();
		session.close();
		return paginate;
	}

	@Override
	public Result insert(Product t) {
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
	public Result update(Product t) {
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
			Product product = session.get(Product.class, id);
			session.remove(product);
			result = new Result(200, "Delete Successfully!");
		} catch (Exception e) {
			result = new Result(500, "Delete Unsuccessful!");			
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}

}
