package it.eforhum.backoffice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.util.DaoFactory;

public class GroupService {
	protected static final Logger log = LogManager.getLogger(GroupService.class);
	
	private static UserGroupsDao userGroupsDao = UserGroupsDao.getInstance();
	
	private static GroupService instance = null;
	
	public static GroupService getInstance() {
		if (instance == null) {
			instance = new GroupService();
		}

		return instance;
	}
	
	public void createGroup(UserGroups group) {
		Objects.requireNonNull(group);

		try {

			group.setCreationTime(LocalDateTime.now());
			group.setCreationUser("app-java");

			userGroupsDao.save(group);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to save a group ", dE);
		}

		log.info("Group has been created {} ", group.getId());
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
	
	public UserGroups findByIdGroup(long id) {
		if (id > 0) {
			return (UserGroups) userGroupsDao.getInstance().findById(id);
		} else {
			log.info("Id must be bigger than 0");
			return null;
		}

	}
	
	public void updateGroup(UserGroups upd) {
		UserGroups g = findByIdGroup(upd.getId());
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
	public List<UserGroups> getAllGroups(){
		return DaoFactory.getUserDao().findAll();
	}
}
