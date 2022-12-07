package it.eforhum.backoffice.dao;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
	
	public String getAllRoles(UserGroups group) {

		Objects.requireNonNull(group);
		log.info("Trying to get Roles List from group {} ", group.getId());
		try (Session sessione = HibernateUtils.getSessionFactory().openSession()) {
			Transaction tx = sessione.beginTransaction();
			NativeQuery query = sessione.createSQLQuery("select roles from user_groups where id=" + group.getId());
			log.info("Roles found {} ", query.getSingleResult());

			return (String) query.getSingleResult();

		}

	}
	
//	public UserGroups findByGroupName(String groupName) {
//		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//			Query query = session.createQuery("from UserGroups where group_name=:groupName");
//			query.setParameter("groupName", groupName);
//			return (UserGroups) query.uniqueResult();
//		}
//
//	}
}
