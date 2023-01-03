package it.eforhum.backoffice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
//	protected static final Logger log = LogManager.getLogger(BaseDao.class);
//
//	private UserDao() {
//		super(User.class);
//	}
//
//	private static UserDao instance = null;
//
//	public static UserDao getInstance() {
//		if (instance == null) {
//			instance = new UserDao();
//		}
//
//		return instance;
//	}
//
//	public User findByUsername(String username) {
//		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//			Query query = session.createQuery("from User where username=:username");
//			query.setParameter("username", username);
//			return (User) query.uniqueResult();
//		}
//
//	}
	
	@Query(value = "SELECT * FROM user where username=?1", nativeQuery = true)
	User findByUsername(String username);
}
