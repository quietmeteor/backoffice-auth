package it.eforhum.backoffice.service;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;

public interface GroupService {
	
	public void createGroup(GroupDTO group);
	
	public void deleteGroup(GroupDTO group);
	
	public GroupDTO findGroupById(long id);
	
	public void updateGroup(GroupDTO upd);
	
}
