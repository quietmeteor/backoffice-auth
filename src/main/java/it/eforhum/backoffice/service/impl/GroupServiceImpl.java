package it.eforhum.backoffice.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.service.GroupService;

public class GroupServiceImpl implements GroupService {
	protected static final Logger log = LogManager.getLogger(GroupServiceImpl.class);

	private static UserGroupsDao userGroupsDao = UserGroupsDao.getInstance();

	private static GroupServiceImpl instance = null;

	public static GroupServiceImpl getInstance() {
		if (instance == null) {
			instance = new GroupServiceImpl();
		}

		return instance;
	}

	@Override
	public void createGroup(GroupDTO group) {
		Objects.requireNonNull(group);

		try {

			UserGroups userToCreate = new UserGroups();
			
			userGroupsDao.save(userToCreate);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to save a group ", dE);
		}

		log.info("Group has been created {} ", group.getId());
	}

	@Override
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

	@Override
	public GroupDTO findByIdGroup(long id) {

		UserGroups group = (UserGroups) userGroupsDao.findById(id);
		ModelMapper mp = new ModelMapper();

		return mp.map(group, new TypeToken<GroupDTO>() {}.getType());

	}

	@Override
	public void updateGroup(GroupDTO updatedGroup) {
		UserGroups groupToUpdate = (UserGroups) userGroupsDao.findById(updatedGroup.getId());

		log.info("Trying to update a group id:{}", groupToUpdate.getId());
		try {

			groupToUpdate.setGroupName(updatedGroup.getGroupName());
			groupToUpdate.setPermissions(updatedGroup.getPermissions());

			StringBuilder sb = new StringBuilder();
			String sep = "";
			for (Roles r : updatedGroup.getRoles()) {
				sb.append(sep);
				sb.append(r.toString());
				sep = ", ";

			}

			groupToUpdate.setRoles(sb.toString());
			groupToUpdate.setEnabled(updatedGroup.isEnabled());
			groupToUpdate.setUpdateTime(LocalDateTime.now());
			groupToUpdate.setUpdateUser("java-app");

			userGroupsDao.update(groupToUpdate);
			log.info("Group id {} has been updated", groupToUpdate.getId());
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a group", dE);
		}

	}
}
