package rw.itcg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rw.itcg.model.Owners;
import rw.itcg.persistence.HibernateUtility;

/**
 * @author NIYOMWUNGERI 11:36:12 PM, Dec 28, 2016
 */
public class OwnerDao extends GenericDao<Owners> {

	Session session = null;
	Query query = null;

	@SuppressWarnings("unchecked")
	public List<Owners> findAll() {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Owners");
		List<Owners> list = query.list();
		session.close();
		return list;
	}
}
