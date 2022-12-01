package it.eforhum.backoffice.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.exception.ServiceException;
import it.eforhum.backoffice.service.RolesService;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.ServiceFactory;

public class RolesServiceImpl implements RolesService {

	private static RolesServiceImpl instance = new RolesServiceImpl();
	protected static final Logger log = LogManager.getLogger(RolesServiceImpl.class);

	private RolesServiceImpl() {

	}

	public static RolesServiceImpl getInstance() {
		return instance;
	}

	public List<String> getRolesList(GroupDTO groupDTO) {
		
		ModelMapper mp = new ModelMapper();

		UserGroups group = mp.map(groupDTO, new TypeToken<UserGroups>() {}.getType());
		
		String rolesString = DaoFactory.getUserGroupDao().getAllRoles(group);
		log.info("Roles String: {} ", rolesString);

//		List<String> rolesList = Arrays.asList(rolesString.split(","));

		List<String> rolesList = new LinkedList<>(Arrays.asList(rolesString.split(",")));

		log.info("Roles list: {} ", rolesList);

		return rolesList;

	}

	public void deleteRole(String role, GroupDTO groupDTO) {

		try {
			
			log.info("Attempting to delete a role: ", role);

			List<Roles> rolesList = groupDTO.getRoles();
			
			if (rolesList.contains(Roles.valueOf(role))) {
				
				rolesList.remove(Roles.valueOf(role));
						
				groupDTO.setRoles(rolesList);
				
				ServiceFactory.getGroupService().updateGroup(groupDTO.getId(), groupDTO);

				log.info("Roles after deleting: {}", groupDTO.getRoles());

			} else {
				throw new ServiceException("Cannot delete a non existing role");
			}

		} catch (ServiceException ex) {

			log.info("Something went wrong while deleting a role: ", ex);
		}

	}

	public void addRole(String role, GroupDTO groupDTO) {
		try {
			
			log.info("Attempting to add a role: {}", role);
			
			List<Roles> rolesList = groupDTO.getRoles();

			if (EnumUtils.isValidEnum(Roles.class, role)) {
				
				log.info(Roles.valueOf(role));
				log.info(rolesList);
				
				rolesList.add(Roles.valueOf(role));
				groupDTO.setRoles(rolesList);

				ServiceFactory.getGroupService().updateGroup(groupDTO.getId(), groupDTO);

				log.info("Updated Roles List: ", groupDTO.getRoles());

			} else {
				throw new ServiceException("Attempted to add a non valid role");
			}

		} catch (ServiceException ex) {

			log.info("Something went wrong while adding a role: ", ex);
		}

	}

}
