package tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import it.eforhum.backoffice.dao.UserDao;
import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
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
	void createUserAndGroupTest() {

		UserGroups userGroup = new UserGroups();
		userGroup.setCreationTime(LocalDateTime.now());
		userGroup.setCreationUser("admin");
		userGroup.setGroupName("GROUP_USERS");
		userGroup.setPermissions("READING, RESERVATION");
		userGroup.setEnabled(true);
		userGroup.setRoles(Roles.USER.toString());

		User user = new User();
		user.setCreationTime(LocalDateTime.now());
		user.setCreationUser("java-app");
		user.setDeleted(false);
		user.setVerified(true);
		user.setEmail("hello1@gmail.com");
		user.setName("Hello");
		user.setLastLogin(LocalDateTime.now());
		user.setLastName("World");
		user.setPassword("qwerty");
		user.setUsername("1a2b3c");
		user.setUserGroup(userGroup);

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().createGroup(userGroup);
		});

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().createUser(user);
		});

		log.info("User with id {} was created", user.getId());
		log.info("UserGroup with id {} was created", userGroup.getId());

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().deleteUserCompletely(user);
		});

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().deleteGroup(userGroup);
		});
		log.info("User deleted");
		log.info("UserGroup deleted");

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
	public void notNullFindUserAndGroupTest() {

		UserGroups userGroup = new UserGroups();
		userGroup.setCreationTime(LocalDateTime.now());
		userGroup.setCreationUser("admin");
		userGroup.setGroupName("GROUP_USERS");
		userGroup.setPermissions("READING, RESERVATION");
		userGroup.setEnabled(true);
		userGroup.setRoles(Roles.USER.toString());

		User user = new User();
		user.setCreationTime(LocalDateTime.now());
		user.setCreationUser("java-app");
		user.setDeleted(false);
		user.setVerified(true);
		user.setEmail("hell555@gmail.com");
		user.setName("Hello");
		user.setLastLogin(LocalDateTime.now());
		user.setLastName("World");
		user.setPassword("qwerty");
		user.setUsername("1a2b3c");
		user.setUserGroup(userGroup);

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().createGroup(userGroup);
		});
		log.info("UserGroup with id {} was created", userGroup.getId());

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().createUser(user);
		});

		log.info("User with id {} was created", user.getId());

		assertNotNull(DaoFactory.getUserDao().getInstance().findById(user.getId()));
		assertNotNull(DaoFactory.getUserGroupDao().getInstance().findById(userGroup.getId()));

		log.info("User found is {}", DaoFactory.getUserDao().getInstance().findById(user.getId()));
		log.info("Group found is {}", DaoFactory.getUserGroupDao().getInstance().findById(userGroup.getId()));

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().deleteUserCompletely(user);
		});

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().deleteGroup(userGroup);
		});
		log.info("User deleted with success");
		log.info("UserGroup deleted with success");

	}

	@Test
	public void updateUserTest() {

		User user = new User();
		user.setCreationTime(LocalDateTime.now());
		user.setCreationUser("java-app");
		user.setDeleted(false);
		user.setVerified(true);
		user.setEmail("hello45555@gmail.com");
		user.setName("Hello");
		user.setLastLogin(LocalDateTime.now());
		user.setLastName("World");
		user.setPassword("qwerty");
		user.setUsername("1a2b3c");

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().createUser(user);
		});

		log.info("User created is {} ", user);

		User updatedUser = new User();
		updatedUser.setUpdateTime(LocalDateTime.now());
		updatedUser.setUpdateUser("java-app");
		updatedUser.setDeleted(false);
		updatedUser.setVerified(true);
		updatedUser.setEmail("theHelloWord11@gmail.com");
		updatedUser.setName("Pryvit");
		updatedUser.setLastLogin(LocalDateTime.now());
		updatedUser.setLastName("Svit");
		updatedUser.setPassword("qwerty");
		updatedUser.setUsername("1a2b3c");

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().updateUser(user.getId(), updatedUser);
		});
		log.info("Updated user is {} ", ServiceFactory.getUserService().getInstance().findByIdUser(user.getId()));

		assertDoesNotThrow(() -> {
			ServiceFactory.getUserService().getInstance().deleteUserCompletely(user);
		});

	}

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
			ServiceFactory.getGroupService().getInstance().updateGroup(userGroup.getId(), updatedGroup);
		});

		log.info("Group has been updated to {} ",
				ServiceFactory.getGroupService().getInstance().findByIdGroup(userGroup.getId()));

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().deleteGroup(userGroup);
		});
	}

	@Test
	void addUserToGroupTest() {
		User user = assertDoesNotThrow(() -> ServiceFactory.getUserService().getInstance().findByIdUser(3));
		UserGroups userGroup = assertDoesNotThrow(() -> ServiceFactory.getGroupService().getInstance().findByIdGroup(15)); // 15 = default user group

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
		User user = ServiceFactory.getUserService().findByUsername("1a2b3c");

		log.info("Found user {} with id {}", user.getUsername(), user.getId());
	}

}