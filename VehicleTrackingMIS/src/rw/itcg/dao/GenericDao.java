package rw.itcg.dao;

import org.hibernate.Session;

import rw.itcg.persistence.HibernateUtility;

/**
 * @author NIYOMWUNGERI
 * @Created Dec 10, 2016 , 10:27:25 PM
 * @GenericDao.java
 */
public class GenericDao<Obj> {
	
	public void saveObject(Obj objec) {
		Session session = HibernateUtility.getConnection();
		session.beginTransaction();
		session.save(objec);
		session.getTransaction().commit();
		session.close();
	}

	public void deleteObject(Obj objec) {
		Session session = HibernateUtility.getConnection();
		session.beginTransaction();
		session.delete(objec);
		session.getTransaction().commit();
		session.close();
	}

	public void updateObject(Obj objec) {
		Session session = HibernateUtility.getConnection();
		session.beginTransaction();
		session.update(objec);
		session.getTransaction().commit();
		session.close();
	}

}
