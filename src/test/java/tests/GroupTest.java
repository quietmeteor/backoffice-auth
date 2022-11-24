package tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import it.eforhum.backoffice.dao.UserGroupsDao;
import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.service.impl.GroupServiceImpl;
import it.eforhum.backoffice.service.impl.UserServiceImpl;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.ServiceFactory;

public class GroupTest {

	protected static final Logger log = LogManager.getLogger(GroupTest.class);

	@Test
	public void testConnection() {
		log.info("Test connessione");
		assertDoesNotThrow(() -> {
			DaoFactory.getUserGroupDao().getInstance().testConnessione();
		});
	}
	
	@Test
	public void createGroupTest() {
		GroupDTO groupDTO = new GroupDTO();
		List<Roles> roleList = new ArrayList<>();
		roleList.add(Roles.USER);
		
		groupDTO.setCreationTime(LocalDateTime.now());
		groupDTO.setCreationUser("admin");
		groupDTO.setEnabled(true);
		groupDTO.setGroupName("Test-group");
		groupDTO.setPermissions("RESERVATIONS");
		groupDTO.setRoles(roleList);
		
		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().createGroup(groupDTO);
		});
		
	}
	
	@Test
	public void deleteGroup() {
		
		GroupDTO groupDTO = ServiceFactory.getGroupService().findGroupById(19);
		
		assertDoesNotThrow(() -> {	
			ServiceFactory.getGroupService().deleteGroup(groupDTO);
		});
		
	}
	
	@Test
	public void updateGroupTest() {
		GroupDTO groupDTO = ServiceFactory.getGroupService().findGroupById(15);

		groupDTO.setCreationTime(LocalDateTime.now());
		groupDTO.setCreationUser("admin");
		groupDTO.setGroupName("GROUP_USERS_TYPE2");
		groupDTO.setPermissions("READING, RESERVATION, EDITING");
		groupDTO.setEnabled(true);

		assertDoesNotThrow(() -> {
			ServiceFactory.getGroupService().getInstance().updateGroup(groupDTO);
		});

		log.info("Group has been updated to {} ",
			ServiceFactory.getGroupService().getInstance().findGroupById(groupDTO.getId()));

	}
	
	@Test
	public void nullIdFindGroupTest() {
		UserGroups user = (UserGroups) UserGroupsDao.getInstance().findById(1000);
		log.info("Non existing group search");
		assertNull(user);
	}
	
	@Test
	public void getAllGroupsTest() {
		assertDoesNotThrow(() -> {
			List<GroupDTO> groupDtoList = GroupServiceImpl.getInstance().getAllGroups();
			log.info(groupDtoList);
		});
	}
	
	
}
