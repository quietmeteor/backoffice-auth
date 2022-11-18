package tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import it.eforhum.backoffice.dao.UserDao;
import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.service.impl.UserServiceImpl;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.ServiceFactory;

public class BaseDAOTest {

	protected static final Logger log = LogManager.getLogger(BaseDAOTest.class);

	@Test
	public void testConnection() {
		log.info("Test connessione");
		assertDoesNotThrow(() -> {
			DaoFactory.getUserDao().getInstance().testConnessione();
		});
	}

	@Test
	public void findAllUsersTest() {
		assertDoesNotThrow(() -> {
			List<UserDTO> userDtoList = UserServiceImpl.getInstance().getAllUsers();
			log.info(userDtoList);
		});
	
	}

	@Test
	public void nullIdFindTest() {

		User user = (User) UserDao.getInstance().findById(1000);
		log.info("Non existing user search");
		assertNull(user);

	}

	@Test
	public void nullIdFindGroupTest() {

		UserGroups user = (UserGroups) UserGroupsDao.getInstance().findById(1000);
		log.info("Non existing group search");
		assertNull(user);

	}

	@Test
	public void updateGroupTest() {
		UserGroups userGroup = new UserGroups();
		userGroup.setCreationTime(LocalDateTime.now());
		userGroup.setCreationUser("admin");
		userGroup.setGroupName("GROUP_USERS");
		userGroup.setPermissions("READING, RESERVATION");
		userGroup.setEnabled(true);
		userGroup.setRoles("USER");

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().createGroup(userGroup);
		});
		log.info("Group created is {} ", userGroup);

		UserGroups updatedGroup = new UserGroups();
		updatedGroup.setCreationTime(LocalDateTime.now());
		updatedGroup.setCreationUser("admin");
		updatedGroup.setGroupName("GROUP_USERS_TYPE2");
		updatedGroup.setPermissions("READING, RESERVATION, EDITING");
		updatedGroup.setEnabled(true);
		updatedGroup.setRoles(Roles.ROLE_UPDATE_SLOT.toString());

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().updateGroup(updatedGroup);
		});

		log.info("Group has been updated to {} ",
				ServiceFactory.getGroupService().getInstance().findByIdGroup(userGroup.getId()));

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().deleteGroup(userGroup);
		});
	}

	@Test
	void addUserToGroupTest() {
		UserDTO user = assertDoesNotThrow(() -> ServiceFactory.getUserService().getInstance().findById(3));
		UserGroups userGroup = assertDoesNotThrow(
				() -> ServiceFactory.getGroupService().getInstance().findByIdGroup(15)); // 15 = default user group

		assertNotNull(user);
		assertNotNull(userGroup);

		log.info("Found user - {}", user.getId());
		log.info("Found group - {}", userGroup.getId());

		log.info("Attempting to add user {} to group {}", user.getUsername(), userGroup.getGroupName());
		assertDoesNotThrow(() -> ServiceFactory.getUserService().getInstance().addUserToGroup(userGroup, user));

		log.info("Succesfully added user {} to group {}", user.getUsername(), userGroup.getGroupName());
	}

	@Test
	void findUserByUsernameTest() {
		UserDTO user = ServiceFactory.getUserService().findByUsername("1a2b3c");

		log.info("Found user {} with id {}", user.getUsername(), user.getId());
	}

	@Test
	public void findRolesTest() {
		UserGroups group = new UserGroups();
		group.setCreationTime(LocalDateTime.now());
		group.setCreationUser("admin");
		group.setGroupName("GROUP_ADMIN_TEST_ROLES");
		group.setPermissions("READING, RESERVATION, EATING");
		group.setEnabled(true);
		group.setRoles(Roles.ROLE_CREATE_USER.toString() + ", " + Roles.ROLE_CREATE_RISORSE.toString() + ", "
				+ Roles.ROLE_CREATE_GROUP.toString());

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().createGroup(group);

			ServiceFactory.getRolesService().getRolesList(group);

		});

	}

	@Test
	public void deleteRolesTest() {
		UserGroups group = new UserGroups();
		group.setCreationTime(LocalDateTime.now());
		group.setCreationUser("admin");
		group.setGroupName("GROUP_ADMIN_TEST_ROLES");
		group.setPermissions("READING, RESERVATION, EATING");
		group.setEnabled(true);
		group.setRoles(Roles.ROLE_CREATE_USER.toString() + ", " + Roles.ROLE_CREATE_RISORSE.toString() + ", "
				+ Roles.ROLE_CREATE_GROUP.toString());

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().createGroup(group);

		});
		
		String role=Roles.ROLE_CREATE_USER.toString();
		
		ServiceFactory.getRolesService().deleteRole(role,  group);
		
		log.info("Test Role deleting; Updated roles: ", group.getRoles());

	}
	
	@Test
	public void addRolesTest() {
		UserGroups group = new UserGroups();
		group.setCreationTime(LocalDateTime.now());
		group.setCreationUser("admin");
		group.setGroupName("GROUP_ADMIN_TEST_ROLES");
		group.setPermissions("READING, RESERVATION, EATING");
		group.setEnabled(true);
		group.setRoles(Roles.ROLE_CREATE_USER.toString() + ", " + Roles.ROLE_CREATE_RISORSE.toString() + ", "
				+ Roles.ROLE_CREATE_GROUP.toString());

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().createGroup(group);

		});
		
		String role=Roles.ROLE_READ_USER.toString();
		
		ServiceFactory.getRolesService().addRole(role,  group);
		
		log.info("Test Role adding Updated roles: ", group.getRoles());

	}
	
	
}