package dao;

import entity.User;

public class UserDao extends BaseDao {

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
