package it.eforhum.backoffice.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	public List<String> getRolesList(UserGroups group) {
		String rolesString = DaoFactory.getUserGroupDao().getAllRoles(group);
		log.info("Roles String: {} ", rolesString);

//		List<String> rolesList = Arrays.asList(rolesString.split(","));

		List<String> rolesList = new LinkedList<>(Arrays.asList(rolesString.split(",")));

		log.info("Roles list: {} ", rolesList);

		return rolesList;

	}

	public void deleteRole(String role, UserGroups group) {

		try {
			List<String> rolesList = getRolesList(group);
			log.info("Attempting to delete a role: ", role);

			if (rolesList.contains(role)) {

				rolesList.remove(role);
				log.info("Roles before deleting: ", rolesList);

				group.setRoles(StringUtils.join(rolesList, ","));
				ServiceFactory.getGroupService().updateGroup(group);

				log.info("Roles after deleting: ", group.getRoles());

			} else {
				throw new ServiceException("Deleting a non existing role");
			}

		} catch (ServiceException ex) {

			log.info("Something went wrong while deleting a role: ", ex);
		}

	}

	public void addRole(String role, UserGroups group) {
		try {
			List<String> rolesList = getRolesList(group);
			log.info("Attempting to add a role: ", role);

			if (EnumUtils.isValidEnum(Roles.class, role)) {

				rolesList.add(role);
				group.setRoles(StringUtils.join(rolesList, ","));

				ServiceFactory.getGroupService().updateGroup(group);
				log.info("Updated Roles List: ", group.getRoles());

			} else {
				throw new ServiceException("Attempted to add a non valid role");
			}

		} catch (ServiceException ex) {

			log.info("Something went wrong while adding a role: ", ex);
		}

	}

}
