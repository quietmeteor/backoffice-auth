package it.eforhum.backoffice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dao.UserDao;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.util.DaoFactory;

public class UserService {
	protected static final Logger log = LogManager.getLogger(UserService.class);

	private static UserDao userDao = UserDao.getInstance();
	
	private static UserService instance = null;

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}

		return instance;
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

	public User findByUsername(String username) {
		log.info("Try to find user by username {} ", username);
		User user = new User();
		try {
			user = userDao.findByUsername(username);
		} catch (ServiceException e) {
			throw new ServiceException("Something went wrong when trying to find user by username", e);
		}
		return user;
	}

	public void updateUser(long id, User updatedUser) {
		User oldUser = findByIdUser(id);
		Objects.requireNonNull(oldUser);
		log.info("Trying to update a user id:{} ", oldUser.getId());
		try {
			if (!updatedUser.getPassword().equals(oldUser.getPassword())) {
				oldUser.setDateModifiedPass(LocalDateTime.now());
			}
			oldUser.setEmail(updatedUser.getEmail());
			oldUser.setName(updatedUser.getName());
			oldUser.setLastName(updatedUser.getLastName());
			oldUser.setPassword(updatedUser.getPassword());

			oldUser.setUpdateTime(LocalDateTime.now());
			oldUser.setUpdateUser("java-app"); // revision
			oldUser.setUsername(updatedUser.getUsername());

			userDao.getInstance().update(oldUser);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a user ", dE);
		}

	}

	public void addUserToGroup(UserGroups group, User user) {
		Objects.requireNonNull(user);
		Objects.requireNonNull(group);

		user.setUserGroup(group);

		try {
			UserDao.getInstance().update(user);
		} catch (DaoException e) {
			throw new ServiceException("Something went wrong when adding user to group", e);
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

			for (String role : ug.getRoles().split(",")) {
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
