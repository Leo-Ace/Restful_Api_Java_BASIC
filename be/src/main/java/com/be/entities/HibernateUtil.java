package com.be.entities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory getSessionFactory() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		return sf;
	}
}
