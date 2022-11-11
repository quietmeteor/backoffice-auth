package it.eforhum.backoffice.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.util.HibernateUtils;

public class UserDao extends BaseDao {
	protected static final Logger log = LogManager.getLogger(BaseDao.class);

	private UserDao() {
		super(User.class);
	}

	private static UserDao instance = null;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}

		return instance;
	}

	public User findByUsername(String username) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Query query = session.createQuery("from User where username=:username");
			query.setParameter("username", username);
			return (User) query.uniqueResult();
		}

	}
}
