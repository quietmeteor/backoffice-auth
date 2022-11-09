package it.eforhum.backoffice.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.util.HibernateUtils;

public class UserGroupsDao extends BaseDao {
	protected static final Logger log = LogManager.getLogger(BaseDao.class);
	
	private UserGroupsDao() {
		super(UserGroups.class);
	}

	private static UserGroupsDao instance = null;

	public static UserGroupsDao getInstance() {
		if (instance == null) {
			instance = new UserGroupsDao();
		}

		return instance;
	}
	
}
