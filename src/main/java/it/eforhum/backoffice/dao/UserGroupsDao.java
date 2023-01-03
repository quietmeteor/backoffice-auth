package it.eforhum.backoffice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.eforhum.backoffice.entity.UserGroups;

public interface UserGroupsDao extends CrudRepository<UserGroups, Integer>{
//	protected static final Logger log = LogManager.getLogger(BaseDao.class);
//	
//	private UserGroupsDao() {
//		super(UserGroups.class);
//	}
//
//	private static UserGroupsDao instance = null;
//
//	public static UserGroupsDao getInstance() {
//		if (instance == null) {
//			instance = new UserGroupsDao();
//		}
//
//		return instance;
//	}
//	
//	public String getAllRoles(UserGroups group) {
//
//		Objects.requireNonNull(group);
//		log.info("Trying to get Roles List from group {} ", group.getId());
//		try (Session sessione = HibernateUtils.getSessionFactory().openSession()) {
//			Transaction tx = sessione.beginTransaction();
//			NativeQuery query = sessione.createSQLQuery("select roles from user_groups where id=" + group.getId());
//			log.info("Roles found {} ", query.getSingleResult());
//
//			return (String) query.getSingleResult();
//
//		}
//
//	}
	
	@Query(value = "SELECT roles FROM user_groups where id=?1", nativeQuery = true)
	String getAllRoles(UserGroups groups);
	
}
