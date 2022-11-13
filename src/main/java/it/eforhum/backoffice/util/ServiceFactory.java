package it.eforhum.backoffice.util;

import it.eforhum.backoffice.service.GroupService;
import it.eforhum.backoffice.service.RolesService;
import it.eforhum.backoffice.service.UserService;
import it.eforhum.backoffice.service.impl.GroupServiceImpl;
import it.eforhum.backoffice.service.impl.RolesServiceImpl;
import it.eforhum.backoffice.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	public static UserServiceImpl getUserService() {
		return UserServiceImpl.getInstance();
	}
	
	public static GroupServiceImpl getGroupService() {
		return GroupServiceImpl.getInstance();
	}
	
	public static RolesServiceImpl getRolesService() {
		return RolesServiceImpl.getInstance();
	}
}
