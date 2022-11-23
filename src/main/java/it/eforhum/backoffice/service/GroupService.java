package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.dto.GroupDTO;

public interface GroupService {
	
	public void createGroup(GroupDTO group);
	
	public void deleteGroup(GroupDTO group);
	
	public GroupDTO findGroupById(long id);
	
	public void updateGroup(GroupDTO upd);
	
	public List<GroupDTO> getAllGroups();
	
}
