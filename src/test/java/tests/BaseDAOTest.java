package tests;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dao.UserDao;
import entity.User;

public class BaseDAOTest {
	
	@Test
	void create(){
		User user = new User();
		user.setName("Andrii");
		user.setLastName("D");
		user.setEmail("andre");
		user.setLastLogin(LocalDateTime.now());
		user.setCreationTime(LocalDateTime.now());
		user.setPassword("12321");
		user.setUsername("aaaa");
		user.setCreationUser("app-java");
		
		
		UserDao.getInstance().save(user);
	}
}
