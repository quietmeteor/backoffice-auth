package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dao.UserDao;
import dao.UserGroupsDao;
import entity.User;
import entity.UserGroups;

public class BaseDAOTest {
	
	@Test
	void create() {
		
		UserGroups userGroups = new UserGroups();
		User user = new User();
		userGroups.setCreationUser("test-user");
		userGroups.setCreationTime(LocalDateTime.now());
		userGroups.setGroupName("TEST GROUP");
		userGroups.setRoles("role");
		userGroups.setPermissions("UPDATE");
		userGroups.setVisibility("all");
		
		assertTrue(userGroups.getId() > 0);;
		
		assertDoesNotThrow(() -> {
			UserGroupsDao.getInstance().save(userGroups);
		});
		
		
		user.setCreationUser("test-user");
		user.setName("test-name");
		user.setLastName("test-last-name");
		user.setLastLogin(LocalDateTime.now());
		user.setEmail("test-mail");
		user.setUsername("test-user");
		user.setPassword("test-psw");
		user.setCreationTime(LocalDateTime.now());
		user.setUserGroup(userGroups);
		
		assertTrue(user.getId() > 0);
		
		assertDoesNotThrow(() -> {
			UserDao.getInstance().save(user);
		});
		
		assertDoesNotThrow(()->{
			UserDao.getInstance().delete(user);
		});
		
		assertDoesNotThrow(()->{
			UserGroupsDao.getInstance().delete(userGroups);
		});
		
	}
	@Test
	void testConnectio() {
		UserDao.getInstance().testConnessione();
	}

	
}
