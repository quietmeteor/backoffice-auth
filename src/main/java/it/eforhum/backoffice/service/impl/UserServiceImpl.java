package it.eforhum.backoffice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eforhum.backoffice.dao.UserDao;
import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.service.UserService;
import it.eforhum.backoffice.util.HibernateUtils;

@Service
public class UserServiceImpl implements UserService {
	protected static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserGroupsDao groupsDao;
	
	public UserServiceImpl() {}

	@Override
	public void createUser(UserDTO userDTO) {
		Objects.requireNonNull(userDTO);
		User user = new User();

		log.info("Checking if user with id {} exists", userDTO.getId());
		User userCheck = userDao.findById(userDTO.getId()).orElseThrow(() -> new EntityNotFoundException());

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
			
			UserGroups userGroups = groupsDao.findById(userDTO.getGroupId()).orElseThrow(() -> new EntityNotFoundException());
			user.setUserGroup(userGroups);

			
			this.userDao.save(user);

			log.info("User has been created id: {} ", user.getId());
		} catch (DaoException dE) {

			throw new ServiceException("Something went wrong while trying to save a new user", dE);

		}

	}

	@Override
	public void deleteUser(UserDTO userToDelete) {
		User user = userDao.findById(userToDelete.getId()).orElseThrow(() -> new EntityNotFoundException());
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
			
			userDao.save(user);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a user", dE);
		}
	}

	@Override
	public void deleteUserCompletely(int id) {
		User userToDelete = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException());

		try {
			log.info("Attempting to delete user with id {} ", id);
			userDao.delete(userToDelete);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a user", dE);
		}

		log.info("User with id {} has been permanently removed", userToDelete.getId());
	}

	@Override
	public UserDTO findById(int id) {
	
		User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException());
		log.info(user);
		ModelMapper mp = new ModelMapper();

		UserDTO userDTO = mp.map(user, new TypeToken<UserDTO>() {}.getType());
		
		return userDTO;
	}

	@Override
	public UserDTO findByUsername(String username) {
		log.info("Attempting to find user by username {} ", username);
		
		UserDTO userDTO = new UserDTO();

		try {

			User user = userDao.findByUsername(username);
			ModelMapper mp = new ModelMapper();
			
			userDTO = mp.map(user, new TypeToken<UserDTO>() {}.getType());
			

		} catch (ServiceException e) {
			throw new ServiceException("Something went wrong when trying to find user by username", e);
		}

		return userDTO;
	}

	@Override
	public void updateUser(UserDTO updatedUser, int id) {
		User oldUser = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException());
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
			
			UserGroups group = groupsDao.findById(updatedUser.getGroupId()).orElseThrow(() -> new EntityNotFoundException());
			oldUser.setUserGroup(group);
			
			userDao.save(oldUser);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a user ", dE);
		}

	}

	@Override
	public void addUserToGroup(GroupDTO group, UserDTO userDTO) {
		Objects.requireNonNull(userDTO);
		Objects.requireNonNull(group);
		
		try {
			
			User user = userDao.findById(userDTO.getId()).orElseThrow(() -> new EntityNotFoundException());
			UserGroups userGroup = groupsDao.findById(group.getId()).orElseThrow(() -> new EntityNotFoundException());
			
			user.setUserGroup(userGroup);
			userDao.save(user);
			
		} catch (DaoException dE){
			throw new ServiceException("Something went wrong when adding user to group", dE);
		}
		
	}
	
	public List<UserDTO> getAllUsers() {
		
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			
			List<User> userList = (List<User>) userDao.findAll();
			ModelMapper mp = new ModelMapper();
			
			return mp.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
		}
		
	}

}
