package tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import dao.UserDao;
import dao.UserGroupsDao;
import entity.User;
import entity.UserGroups;
import service.BackofficeService;

public class BaseDAOTest {

	protected static final Logger log = LogManager.getLogger(BaseDAOTest.class);

	@Test
	public void testConnection() {
		log.info("Test connessione");
		assertDoesNotThrow(() -> {
			UserDao.getInstance().testConnessione();
		});

	}

	@Test
	void createUserAndGroupTest() {

		UserGroups g = new UserGroups();
		g.setCreationTime(LocalDateTime.now());
		g.setCreationUser("admin");
		g.setGroupName("GROUP_USERS");
		g.setPermissions("READING, RESERVATION");
		g.setVisibility("users_calendar");
		g.setRoles("USER");

		User u = new User();
		u.setCreationTime(LocalDateTime.now());
		u.setCreationUser("java-app");
		u.setDeleted(false);
		u.setVerified(true);
		u.setEmail("hello1@gmail.com");
		u.setName("Hello");
		u.setLastLogin(LocalDateTime.now());
		u.setLastName("World");
		u.setPassword("qwerty");
		u.setUsername("1a2b3c");
		u.setUserGroup(g);

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().createGroup(g);
		});

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().createUser(u);
		});

		log.info("User with id {} was created", u.getId());
		log.info("UserGroup with id {} was created", g.getId());

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().deleteUserCompletely(u);
		});

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().deleteGroup(g);
		});
		log.info("User deleted");
		log.info("UserGroup deleted");

	}

	@Test
	public void nullIdFindTest() {

		User u = (User) UserDao.getInstance().findById(1000);
		log.info("Non existing user search");
		assertNull(u);

	}

	@Test
	public void nullIdFindGroupTest() {

		UserGroups u = (UserGroups) UserGroupsDao.getInstance().findById(1000);
		log.info("Non existing group search");
		assertNull(u);

	}

	@Test
	public void notNullFindUserAndGroupTest() {

		UserGroups g = new UserGroups();
		g.setCreationTime(LocalDateTime.now());
		g.setCreationUser("admin");
		g.setGroupName("GROUP_USERS");
		g.setPermissions("READING, RESERVATION");
		g.setVisibility("users_calendar");
		g.setRoles("USER");

		User u = new User();
		u.setCreationTime(LocalDateTime.now());
		u.setCreationUser("java-app");
		u.setDeleted(false);
		u.setVerified(true);
		u.setEmail("hello3@gmail.com");
		u.setName("Hello");
		u.setLastLogin(LocalDateTime.now());
		u.setLastName("World");
		u.setPassword("qwerty");
		u.setUsername("1a2b3c");
		u.setUserGroup(g);

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().createGroup(g);
		});

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().createUser(u);
		});

		log.info("User with id {} was created", u.getId());
		log.info("UserGroup with id {} was created", g.getId());

		assertNotNull(UserDao.getInstance().findById(u.getId()));
		assertNotNull(UserGroupsDao.getInstance().findById(g.getId()));
		log.info("User found is {}", UserDao.getInstance().findById(u.getId()));
		log.info("Group found is {}", UserGroupsDao.getInstance().findById(g.getId()));

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().deleteUserCompletely(u);
		});

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().deleteGroup(g);
		});
		log.info("User deleted");
		log.info("UserGroup deleted");

	}

	@Test
	public void updateUserTest() {

		User u = new User();
		u.setCreationTime(LocalDateTime.now());
		u.setCreationUser("java-app");
		u.setDeleted(false);
		u.setVerified(true);
		u.setEmail("hello4@gmail.com");
		u.setName("Hello");
		u.setLastLogin(LocalDateTime.now());
		u.setLastName("World");
		u.setPassword("qwerty");
		u.setUsername("1a2b3c");

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().createUser(u);
		});

		log.info("User created is {} ", u);

		User u2 = new User();
//		u2.setUpdateTime(LocalDateTime.now());
//		u2.setUpdateUser("java-app");

//		u2.setDeleted(false);
//		u2.setVerified(true);
		u2.setEmail("theHelloWord@gmail.com");
		u2.setName("Pryvit");
		u2.setLastLogin(LocalDateTime.now());
		u2.setLastName("Svit");
		u2.setPassword("qwerty");
		u2.setUsername("1a2b3c");

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().updateUser(u.getId(), u2);
		});
		log.info("Updated user is {} ", BackofficeService.getInstance().findByIdUser(u.getId()));

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().deleteUserCompletely(u);
		});

	}

	public void updateGroupTest() {
		UserGroups g = new UserGroups();
		g.setCreationTime(LocalDateTime.now());
		g.setCreationUser("admin");
		g.setGroupName("GROUP_USERS");
		g.setPermissions("READING, RESERVATION");
		g.setVisibility("users_calendar");
		g.setRoles("USER");

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().createGroup(g);
		});
		
		UserGroups g2 = new UserGroups();
		g2.setCreationTime(LocalDateTime.now());
		g2.setCreationUser("admin");
		g2.setGroupName("GROUP_USERS_TYPE2");
		g2.setPermissions("READING, RESERVATION, EDITING");
		g2.setVisibility("users_calendar");
		g2.setRoles("USER, EDITOR");
		
		assertDoesNotThrow(()->{
			BackofficeService.getInstance().updateGroup(g.getId(), g2);
		});
		
		log.info("Group has been updated to {} ", BackofficeService.getInstance().findByIdGroup(g.getId()));

		assertDoesNotThrow(() -> {
			BackofficeService.getInstance().deleteGroup(g);
		});
	}
	
	

}
