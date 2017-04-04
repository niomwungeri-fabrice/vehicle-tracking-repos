package rw.itcg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rw.itcg.model.Car;
import rw.itcg.persistence.HibernateUtility;

/**
 * @author NIYOMWUNGERI 11:34:59 PM, Dec 28, 2016
 */
public class CarDao extends GenericDao<Car> {
	Session session = null;
	Query query = null;

	@SuppressWarnings("unchecked")
	public List<Car> findCarByPlatNber(Car car) {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Car WHERE carPlate = ?");
		query.setString(0, car.getCarPlate());
		return query.list();
	}

	public Car CarByPlatNber(String car) {
		session = HibernateUtility.getConnection();
		Car c = (Car) session.get(Car.class, car);
		session.close();
		return c;
	}

	@SuppressWarnings("unchecked")
	public List<Car> CarByPlatNberList(String car) {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Car WHERE carPlate = ?");
		query.setString(0, car);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Car> findAllCars() {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Car");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Car> findCarByOwner(String nid) {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Car WHERE ownerId = ?");
		query.setString(0, nid);
		return query.list();
	}
}
