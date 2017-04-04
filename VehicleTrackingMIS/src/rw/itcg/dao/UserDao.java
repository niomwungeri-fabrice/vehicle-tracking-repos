package rw.itcg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rw.itcg.model.Users;
import rw.itcg.persistence.HibernateUtility;

/**
 * @author NIYOMWUNGERI 8:46:26 AM, Dec 27, 2016
 */
public class UserDao extends GenericDao<Users> {
	Session session = null;
	Query query = null;

	public Users findUserByUsername(String username) {
		session = HibernateUtility.getConnection();
		Users u = (Users) session.get(Users.class, username);
		session.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<Users> findAll() {
		session = HibernateUtility.getConnection();
		query = session.createQuery("FROM Users");
		List<Users> list = query.list();
		session.close();
		return list;
	}

}
