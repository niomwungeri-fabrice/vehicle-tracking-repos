package rw.itcg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rw.itcg.model.Mitation;
import rw.itcg.persistence.HibernateUtility;

/**
 * @author NIYOMWUNGERI 7:12:42 AM, Dec 29, 2016
 */
public class MitationDao extends GenericDao<Mitation> {

	Session session = null;
	Query query = null;

	@SuppressWarnings("unchecked")
	public List<Mitation> findCarByPlatNber(String carPlate) {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Mitation WHERE carId = ?");
		query.setString(0, carPlate);
		return query.list();
	}

	public Mitation CarByPlatNber(String carPlate) {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Mitation WHERE carId = ?");
		query.setString(0, carPlate);
		return (Mitation) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Mitation> findAll() {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Mitation");
		List<Mitation> list = query.list();
		session.close();
		return list;

	}
}
