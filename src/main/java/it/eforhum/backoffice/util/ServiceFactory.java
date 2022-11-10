package it.eforhum.backoffice.util;

import it.eforhum.backoffice.service.GroupService;
import it.eforhum.backoffice.service.RolesService;
import it.eforhum.backoffice.service.UserService;

public class ServiceFactory {
	
	public static UserService getUserService() {
		return UserService.getInstance();
	}
	
	public static GroupService getGroupService() {
		return GroupService.getInstance();
	}
	
	public static RolesService getRolesService() {
		return RolesService.getInstance();
	}
}
