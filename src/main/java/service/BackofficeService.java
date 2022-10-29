package service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UserDao;
import dao.UserGroupsDao;
import entity.User;
import entity.UserGroups;
import exception.DaoException;
import exception.ServiceException;

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

	public void createUser(User user){
        Objects.requireNonNull(user);
		try{
			user.setCreationTime(LocalDateTime.now());
			userDao.save(user);
		}catch(DaoException dE){
			throw new ServiceException("Something went wrong while trying to save a new user", dE);
		}
        log.info("User has been created {} ", user.getId());
    }

	public void deleteUser(User userToDelete){
		User user = new User();
		Objects.requireNonNull(userToDelete);
        try{
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
        	
        	this.userDao.update(user);
        }catch(DaoException dE ){
            throw new ServiceException("Something went wrong while trying to delete a user", dE);
            
        }
		log.info("User has been deleted {}", userToDelete.getId());
    }

	public void deleteGroup(UserGroups group) {
		Objects.requireNonNull(group);
		try{	
			log.info("Attempting group with {} id removal", group.getId());
			userGroupsDao.delete(group);
		}catch(DaoException dE){
			throw new ServiceException("Something went wrong while trying to delete a group", dE);
		}
		
		log.info("Group id {} removed", group.getId());
	}

	

	


}
