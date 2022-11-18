package it.eforhum.backoffice.service;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;

public interface GroupService {
	
	public void createGroup(GroupDTO group);
	
	public void deleteGroup(UserGroups group);
	
	public GroupDTO findByIdGroup(long id);
	
	public void updateGroup(GroupDTO upd);
	
}
