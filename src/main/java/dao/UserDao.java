package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import entity.User;
import util.HibernateUtils;

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
		try (Session sessione = HibernateUtils.getSessionFactory().openSession()) {
//			NativeQuery query = sessione.createNativeQuery("SELECT * FROM user WHERE username = " + username, UserDao.class);
			log.info("Attempting to find user by username {}", username);

			return sessione.find(User.class, username);
		}

	}

}
