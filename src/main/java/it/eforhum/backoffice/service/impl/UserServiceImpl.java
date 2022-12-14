package it.eforhum.backoffice.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import it.eforhum.backoffice.dao.UserDao;
import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.service.UserService;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.HibernateUtils;
import it.eforhum.backoffice.util.ServiceFactory;

public class UserServiceImpl implements UserService {
	protected static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	private static UserDao userDao = UserDao.getInstance();

	private static UserServiceImpl instance = null;
	
	private UserServiceImpl() {}

	public static UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}

		return instance;
	}

	@Override
	public void createUser(UserDTO userDTO) {
		Objects.requireNonNull(userDTO);
		User user = new User();

		log.info("Checking if user with id {} exists", userDTO.getId());
		User userCheck = (User) UserDao.getInstance().findById(userDTO.getId());

		if (Objects.nonNull(userCheck)) {
			log.info("User already exists");
			return;
		}

		try {

			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setName(userDTO.getName());
			user.setLastName(userDTO.getLastName());
			user.setEmail(userDTO.getEmail());
			user.setVerified(userDTO.isVerified());
			user.setDeleted(userDTO.isDeleted());
			user.setCreationUser("admin");
			user.setCreationTime(LocalDateTime.now());
			user.setLastLogin(LocalDateTime.now());
			
			UserGroups userGroups = (UserGroups) DaoFactory.getUserGroupDao().findById(userDTO.getGroupId());
			user.setUserGroup(userGroups);

			
			this.userDao.save(user);

			log.info("User has been created id: {} ", user.getId());
		} catch (DaoException dE) {

			throw new ServiceException("Something went wrong while trying to save a new user", dE);

		}

	}

	@Override
	public void deleteUser(UserDTO userToDelete) {
		User user = (User) userDao.findById(userToDelete.getId());
		Objects.requireNonNull(userToDelete);

		try {
			
			user.setName(userToDelete.getName());
			user.setLastName(userToDelete.getLastName());
			user.setUsername("");
			user.setPassword("");
			user.setEmail(userToDelete.getEmail());
			user.setLastLogin(LocalDateTime.now());
			user.setDateModifiedPass(LocalDateTime.now());
			user.setDeleted(true);
			user.setVerified(false);
			user.setCreationUser("admin");
			
			userDao.update(user);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a user", dE);
		}
	}

	@Override
	public void deleteUserCompletely(long id) {
		User userToDelete = (User) userDao.getInstance().findById(id);

		try {
			log.info("Attempting to delete user with id {} ", id);
			userDao.delete(userToDelete);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a user", dE);
		}

		log.info("User with id {} has been permanently removed", userToDelete.getId());
	}

	@Override
	public UserDTO findById(long id) {
	
		User user = (User) userDao.findById(id);
		ModelMapper mp = new ModelMapper();

		UserDTO userDTO = mp.map(user, new TypeToken<UserDTO>() {}.getType());
		
		return userDTO;
	}

	@Override
	public UserDTO findByUsername(String username) {
		log.info("Attempting to find user by username {} ", username);
		
		UserDTO userDTO = new UserDTO();

		try {

			User user = (User) userDao.findByUsername(username);
			ModelMapper mp = new ModelMapper();
			
			userDTO = mp.map(user, new TypeToken<UserDTO>() {}.getType());
			

		} catch (ServiceException e) {
			throw new ServiceException("Something went wrong when trying to find user by username", e);
		}

		return userDTO;
	}

	@Override
	public void updateUser(UserDTO updatedUser, long id) {
		User oldUser = (User) userDao.findById(id);
		Objects.requireNonNull(oldUser);

		log.info("Trying to update user with id:{} ", oldUser.getId());

		try {
			if (!updatedUser.getPassword().equals(oldUser.getPassword())) {
				oldUser.setDateModifiedPass(LocalDateTime.now());
			}
			oldUser.setEmail(updatedUser.getEmail());
			oldUser.setName(updatedUser.getName());
			oldUser.setLastName(updatedUser.getLastName());
			oldUser.setPassword(updatedUser.getPassword());
			oldUser.setVerified(updatedUser.isVerified());
			oldUser.setDeleted(updatedUser.isDeleted());
			oldUser.setUpdateTime(LocalDateTime.now());
			oldUser.setUpdateUser("java-app"); // revision
			oldUser.setUsername(updatedUser.getUsername());
			
			UserGroups group = (UserGroups) DaoFactory.getUserGroupDao().findById(updatedUser.getGroupId());
			oldUser.setUserGroup(group);
			
			userDao.getInstance().update(oldUser);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a user ", dE);
		}

	}

	@Override
	public void addUserToGroup(GroupDTO group, UserDTO userDTO) {
		Objects.requireNonNull(userDTO);
		Objects.requireNonNull(group);
		
		try {
			
			User user = (User) userDao.getInstance().findById(userDTO.getId());
			UserGroups userGroup = (UserGroups) UserGroupsDao.getInstance().findById(group.getId());
			
			user.setUserGroup(userGroup);
			userDao.getInstance().update(user);
			
		} catch (DaoException dE){
			throw new ServiceException("Something went wrong when adding user to group", dE);
		}
		
	}
	
	public List<UserDTO> getAllUsers() {
		
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			
			List<User> userList = userDao.findAll();
			ModelMapper mp = new ModelMapper();
			
			return mp.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
		}
		
	}

}
