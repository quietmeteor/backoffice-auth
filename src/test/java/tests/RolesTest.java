package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.ServiceFactory;

public class RolesTest {
	
	protected static final Logger log = LogManager.getLogger(RolesTest.class);

	@Test
	public void getRolesListTest() {
		
		log.info("Searching for all roles in a given group test start");
		assertDoesNotThrow(() ->{
			
			GroupDTO groupDTO = ServiceFactory.getGroupService().findGroupById(19);
			log.info("Found group {}", groupDTO);
			
			List<String> rolesList = ServiceFactory.getRolesService().getRolesList(groupDTO);
			log.info("Found roles {}", rolesList);
			
		});
	}
	
	@Test
	public void addRoleTest() {
		
		log.info("Add a role to a given group test start");
		
		assertDoesNotThrow(() ->{
			GroupDTO groupDTO = ServiceFactory.getGroupService().findGroupById(19);
			ServiceFactory.getRolesService().addRole("ROLE_CREATE_USER", groupDTO);
			
			List<String> rolesList = ServiceFactory.getRolesService().getRolesList(groupDTO);
			log.info("List of roles after adding a role: {}", rolesList);
			
			ServiceFactory.getRolesService().deleteRole("ROLE_CREATE_USER", groupDTO);
		});
	}
	
	@Test
	public void deleteRoleTest() {
		
		log.info("Remove a role from a given group test start");
		
		assertDoesNotThrow(() ->{
			GroupDTO groupDTO = ServiceFactory.getGroupService().findGroupById(19);
			ServiceFactory.getRolesService().deleteRole("ROLE_CREATE_USER", groupDTO);
			
			log.info("List of roles after removing a role: {}", groupDTO.getRoles());
			
			ServiceFactory.getRolesService().addRole("ROLE_CREATE_USER", groupDTO);
		});
	}
	
}
