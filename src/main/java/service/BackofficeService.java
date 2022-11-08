package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import dao.UserDao;
import dao.UserGroupsDao;
import dto.UserDTO;
import entity.User;
import entity.UserGroups;
import enums.Roles;
import exception.DaoException;
import exception.ServiceException;
import util.HibernateUtils;

public class BackofficeService {
	protected static final Logger log = LogManager.getLogger(BackofficeService.class);

	private static UserDao userDao = UserDao.getInstance();
	private static UserGroupsDao userGroupsDao = UserGroupsDao.getInstance();

	private static BackofficeService instance = null;

	public static BackofficeService getInstance() {
		if (instance == null) {
			instance = new BackofficeService();
		}

		return instance;
	}

	public void createGroup(UserGroups group) {
		Objects.requireNonNull(group);

		try {

//			group.setCreationTime(LocalDateTime.now()); ask front-end group
//			group.setCreationUser("app-java");

			userGroupsDao.save(group);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to save a group ", dE);
		}

		log.info("Group has been created {} ", group.getId());
	}

	public void createUser(User user) {
		Objects.requireNonNull(user);
		try {
			user.setCreationTime(LocalDateTime.now());
			userDao.save(user);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to save a new user", dE);
		}
		log.info("User has been created {} ", user.getId());
	}

	public void deleteUser(User userToDelete) {
		User user = new User();
		Objects.requireNonNull(userToDelete);
		try {
			user.setId(userToDelete.getId());
			user.setName(userToDelete.getName());
			user.setLastName(userToDelete.getLastName());
			user.setUsername("");
			user.setPassword("");
			user.setEmail(null);
			user.setLastLogin(userToDelete.getLastLogin());
			user.setDateModifiedPass(userToDelete.getDateModifiedPass());
			user.setDeleted(true);
			user.setVerified(false);
			user.setCreationUser(userToDelete.getCreationUser());

			this.userDao.update(user);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a user", dE);

		}
		log.info("User has been deleted {}", userToDelete.getId());
	}

	public void deleteGroup(UserGroups group) {
		Objects.requireNonNull(group);
		try {
			log.info("Attempting group with {} id removal", group.getId());
			userGroupsDao.delete(group);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a group", dE);
		}

		log.info("Group id {} removed", group.getId());
	}

	public void deleteUserCompletely(User u) {
		Objects.requireNonNull(u);
		try {
			log.info("Attempting user with {} id removal", u.getId());
			userDao.delete(u);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a user", dE);
		}

		log.info("Group id {} removed", u.getId());
	}

	public User findByIdUser(long id) {
		if (id > 0) {

			return (User) UserDao.getInstance().findById(id);
		} else {
			log.info("Id must be bigger than 0");
			return null;
		}

	}

	public UserGroups findByIdGroup(long id) {
		if (id > 0) {
			return (UserGroups) userGroupsDao.getInstance().findById(id);
		} else {
			log.info("Id must be bigger than 0");
			return null;
		}

	}

	public void updateUser(long id, User upd) { // to rewiev
		User u = findByIdUser(id);
		Objects.requireNonNull(u);
		log.info("Trying to update a user id:{} ", u.getId());
		try {
			u.setEmail(upd.getEmail());
			u.setName(upd.getName());
			u.setLastName(upd.getLastName());
			u.setPassword(upd.getPassword());
			// u.setDateModifiedPass(LocalDateTime.now()); revision
			u.setUpdateTime(LocalDateTime.now());
			u.setUpdateUser("java-app"); // revision
			u.setUsername(upd.getUsername());

			userDao.getInstance().update(u);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a user ", dE);
		}

	}

	public void updateGroup(long id, UserGroups upd) {
		UserGroups g = findByIdGroup(id);
		Objects.requireNonNull(g);
		log.info("Trying to update a group id:{}", g.getId());
		try {

			g.setGroupName(upd.getGroupName());
			g.setPermissions(upd.getPermissions());
			g.setRoles(upd.getRoles());
			g.setEnabled(upd.isEnabled());
			g.setUpdateTime(LocalDateTime.now());
			g.setUpdateUser("java-app");

			userGroupsDao.update(g);
			log.info("Group id {} has been updated", g.getId());
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a group", dE);
		}

	}

	public void addUserToGroup(UserGroups group, User user) {
		Objects.requireNonNull(user);
		Objects.requireNonNull(group);

		try {
			user = (User) userDao.findById(user.getId());
			group = (UserGroups) userGroupsDao.findById(group.getId());

			log.info("User and group was succesfully found | user - {} group - {}", user.getId(), group.getId());
		} catch (DaoException e) {
			throw new ServiceException("Something went wrong when finding user and group", e);
		}
		
		try(Session s=HibernateUtils.getSessionFactory().openSession()){
			s.refresh(group);	
		Set<User> userSet = group.getUsers();
		userSet.add(user);

		user.setUserGroup(group);
		group.setUsers(userSet);
		}
	}

	public UserDTO login(String username, String password, String email) {
		UserDTO uDTO = new UserDTO();
		User user = userDao.findByUsername(username);
		UserGroups ug = user.getUserGroup();
		List<Roles> roleList = new ArrayList<>();

		if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
			uDTO.setEmail(user.getEmail());
			uDTO.setGroup(user.getUserGroup());
			uDTO.setLastName(user.getLastName());

			for (String role : ug.getRoles().split(";")) {
				roleList.add(Roles.valueOf(role));
			}

			uDTO.setListRoles(roleList);
			uDTO.setName(user.getName());
			
		} else {
			log.error("Wrong credentials");
		}

		return null;

	}
}
