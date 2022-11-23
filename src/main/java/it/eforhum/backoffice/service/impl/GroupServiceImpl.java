package it.eforhum.backoffice.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.service.GroupService;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.HibernateUtils;
import it.eforhum.backoffice.util.ServiceFactory;

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
	public void createGroup(GroupDTO groupDTO) {
		Objects.requireNonNull(groupDTO);

		try {
			
			ModelMapper mp = new ModelMapper();
			UserGroups newGroup = mp.map(groupDTO, new TypeToken<UserGroups>(){}.getType());
			
			String roles = groupDTO.getRoles().stream()
					.map(n -> String.valueOf(n))
					.collect(Collectors.joining(",", "", ""));
			
			newGroup.setRoles(roles);
			
			userGroupsDao.save(newGroup);
			log.info("Group has been created {} ", newGroup.getId());
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to save a group ", dE);
		}

	}

	@Override
	public void deleteGroup(GroupDTO group) {
		Objects.requireNonNull(group);
		
		try {
			UserGroups groupToDelete = (UserGroups) DaoFactory.getUserGroupDao().findById(group.getId());
			
			log.info("Attempting to delete group with id: {}", group.getId());
			userGroupsDao.delete(groupToDelete);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a group", dE);
		}

		log.info("Group with id {} removed", group.getId());
	}

	@Override
	public GroupDTO findGroupById(long id) {

		UserGroups group = (UserGroups) userGroupsDao.findById(id);
		ModelMapper mp = new ModelMapper();
		
		GroupDTO groupDTO = mp.map(group, new TypeToken<GroupDTO>() {}.getType());
		List<String> rolesStringList = ServiceFactory.getRolesService().getRolesList(groupDTO);
		
		List<Roles> rolesEnumList = new ArrayList<>();
		
		for(String role : rolesStringList) {
			
			log.info("Trying to format role: {} ", role);
			
			role = role.toUpperCase().trim();
			role = role.replaceAll("[\\[\\]\\(\\)]", "");
			
			log.info("Trying to add role {} after formatting", role);
			rolesEnumList.add(Roles.valueOf(role));
		}
		
		groupDTO.setRoles(rolesEnumList);

		return groupDTO;

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
	
	@Override
	public List<GroupDTO> getAllGroups() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			
			List<UserGroups> groupList = userGroupsDao.findAll();
			ModelMapper mp = new ModelMapper();
			List<GroupDTO> groupListDTO = mp.map(groupList, new TypeToken<List<GroupDTO>>(){}.getType());
			
			for(GroupDTO groupDTO : groupListDTO) {
				
				List<String> rolesStringList = ServiceFactory.getRolesService().getRolesList(groupDTO);
				
				List<Roles> rolesEnumList = new ArrayList<>();
				
				for(String role : rolesStringList) {
					
					log.info("Trying to format role: {} ", role);
					
					role = role.toUpperCase().trim();
					role = role.replaceAll("[\\[\\]\\(\\)]", "");
					
					log.info("Trying to add role {} after formatting", role);
					rolesEnumList.add(Roles.valueOf(role));
				}
				
				groupDTO.setRoles(rolesEnumList);
			}
			
			return groupListDTO;
			
		}
	}
	
}
