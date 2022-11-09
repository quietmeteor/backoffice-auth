package it.eforhum.backoffice.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.entity.UserGroups;

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
