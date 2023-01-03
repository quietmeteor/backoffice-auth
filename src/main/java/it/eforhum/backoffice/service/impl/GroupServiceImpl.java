package it.eforhum.backoffice.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.exception.DaoException;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.service.GroupService;
import it.eforhum.backoffice.service.RolesService;
import it.eforhum.backoffice.util.HibernateUtils;

@Service
public class GroupServiceImpl implements GroupService {
	protected static final Logger log = LogManager.getLogger(GroupServiceImpl.class);

	@Autowired
	private UserGroupsDao groupsDao;
	
	private RolesService rolesService;

	public GroupServiceImpl(@Lazy RolesService rolesService) {
		this.rolesService = rolesService;
	}

	@Override
	public GroupDTO createGroup(GroupDTO groupDTO) {
		Objects.requireNonNull(groupDTO);

		try {
			
			ModelMapper mp = new ModelMapper();
			UserGroups newGroup = mp.map(groupDTO, new TypeToken<UserGroups>(){}.getType());
			
			String roles = groupDTO.getRoles().stream()
					.map(n -> String.valueOf(n))
					.collect(Collectors.joining(",", "", ""));
			
			newGroup.setRoles(roles);
			
			groupsDao.save(newGroup);
			log.info("Group has been created {} ", newGroup.getId());
			
			
			return this.findGroupById(newGroup.getId());

		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to save a group ", dE);
		}

	}

	@Override
	public void deleteGroup(int id) {
	
		try {
			UserGroups groupToDelete = groupsDao.findById(id).orElseThrow(() -> new EntityNotFoundException());
			
			log.info("Attempting to delete group with id: {}", id);
			groupsDao.delete(groupToDelete);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while trying to delete a group", dE);
		}

		log.info("Group with id {} removed", id);

	}

	@Override
	public GroupDTO findGroupById(int id) {

		log.info("Trying to find group with id - {}", id);
		UserGroups group = groupsDao.findById(id).orElseThrow(() -> new EntityNotFoundException());
		ModelMapper mp = new ModelMapper();
		
		GroupDTO groupDTO = mp.map(group, new TypeToken<GroupDTO>() {}.getType());
		List<String> rolesStringList = rolesService.getRolesList(groupDTO);
		
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
	public void updateGroup(int id, GroupDTO updatedGroup) {
		UserGroups groupToUpdate = groupsDao.findById(id).orElseThrow(() -> new EntityNotFoundException());

		log.info("Trying to update a group id:{}", id);
		try {

			groupToUpdate.setGroupName(updatedGroup.getGroupName());
			groupToUpdate.setPermissions(updatedGroup.getPermissions());

			StringBuilder sb = new StringBuilder();
			String sep = "";
			List <Roles> rl = updatedGroup.getRoles();
			for (Roles r : updatedGroup.getRoles()) {
				sb.append(sep);
				sb.append(r.toString());
				
				sep = ", ";

			}
			
			groupToUpdate.setCreationTime(updatedGroup.getCreationTime());
			groupToUpdate.setRoles(sb.toString());
			groupToUpdate.setEnabled(updatedGroup.isEnabled());
			groupToUpdate.setUpdateTime(LocalDateTime.now());
			groupToUpdate.setUpdateUser(updatedGroup.getUpdateUser());
			groupToUpdate.setCreationUser(updatedGroup.getCreationUser());
			

			groupsDao.save(groupToUpdate);
			log.info("Group id {} has been updated", id);
		} catch (DaoException dE) {
			throw new ServiceException("Something went wrong while updating a group", dE);
		}

	}
	
	@Override
	public List<GroupDTO> getAllGroups() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			
			List<UserGroups> groupList = (List<UserGroups>) groupsDao.findAll();
			ModelMapper mp = new ModelMapper();
			List<GroupDTO> groupListDTO = mp.map(groupList, new TypeToken<List<GroupDTO>>(){}.getType());
			
			for(GroupDTO groupDTO : groupListDTO) {
				
				List<String> rolesStringList = rolesService.getRolesList(groupDTO);
				
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
