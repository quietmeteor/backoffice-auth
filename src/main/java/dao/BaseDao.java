package dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.BaseEntity;
import entity.User;
import util.HibernateUtils;

public class BaseDao<T extends BaseEntity> {
	protected static final Logger log = LogManager.getLogger(BaseDao.class);
	private final Class<T> clazz;

	protected BaseDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public boolean testConnessione() {
		log.debug("try to open session");

		boolean result = false;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			if (session != null) {
				result = true;
			}
		}
		log.debug("result {}", result);

		return result;
	}

	public T findById(int id) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.find(clazz, id);
		}
	}

	public List<T> findAll() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.createQuery("from " + clazz.getName(), clazz).getResultList();
		}
	}

	public void save(T entity) {
		log.debug("try to save entity {}", entity);
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(entity);
			tx.commit();
			log.debug("entity saved {}", entity.getId());
		}
	}

	public void update(T entity) {
		log.debug("try to update item {}", entity);
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
			log.debug("entity updated");
		}
	}

	public void delete(T entity) {
		log.debug("try to delete item {}", entity);
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
			log.debug("entity deleted");
		}
	}
}