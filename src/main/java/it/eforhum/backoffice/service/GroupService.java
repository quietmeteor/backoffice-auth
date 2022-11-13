package it.eforhum.backoffice.service;

import it.eforhum.backoffice.entity.UserGroups;

public interface GroupService {
	
	public void createGroup(UserGroups group);
	
	public void deleteGroup(UserGroups group);
	
	public UserGroups findByIdGroup(long id);
	
	public void updateGroup(UserGroups upd);
	
}
