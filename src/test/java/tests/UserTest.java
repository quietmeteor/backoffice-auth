package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import it.eforhum.backoffice.dao.UserDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.service.impl.UserServiceImpl;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.ServiceFactory;

public class UserTest {

	protected static final Logger log = LogManager.getLogger(UserTest.class);

	@Test
	public void testConnection() {
		log.info("Test connessione");
		assertDoesNotThrow(() -> {
			DaoFactory.getUserDao().getInstance().testConnessione();
		});
	}
	
	@Test
	public void createUserTest() {
		UserDTO userDTO = new UserDTO();
		userDTO.setDateModifiedPass(LocalDateTime.now());
		userDTO.setDeleted(false);
		userDTO.setEmail("aa@gmail.com");
		userDTO.setLastLogin(LocalDateTime.now());
		userDTO.setLastName("lastname");
		userDTO.setName("firstname");
		userDTO.setPassword("passwordpassword");
		userDTO.setVerified(true);
		userDTO.setUsername("usernameeeee");
		userDTO.setGroupName("GROUP_USERS");
		
		assertDoesNotThrow(() -> {
			UserServiceImpl.getInstance().createUser(userDTO);
		});
		
		assertDoesNotThrow(() -> {
			UserServiceImpl.getInstance().deleteUserCompletely(userDTO.getId());
		});
	}
	
	@Test
	public void deleteUserTest() {
		UserDTO userDTO = UserServiceImpl.getInstance().findById(20);
		
		assertDoesNotThrow(() -> {
			UserServiceImpl.getInstance().deleteUser(userDTO);
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
	void addUserToGroupTest() {
		UserDTO user = assertDoesNotThrow(() -> ServiceFactory.getUserService().findById(3));
		GroupDTO userGroup = assertDoesNotThrow(() ->  ServiceFactory.getGroupService().getInstance().findGroupById(19)); 
		
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
		
		assertNotNull(user);
		log.info("Found user {} with id {}", user.getUsername(), user.getId());
		log.info(user);
	}
	
} 