package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.User;

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
	
}
