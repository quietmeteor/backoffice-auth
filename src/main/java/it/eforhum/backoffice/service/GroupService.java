package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.dto.GroupDTO;

public interface GroupService {
	
	public void createGroup(GroupDTO group);
	
	public GroupDTO findGroupById(long id);

	public void deleteGroup(long id);	
	
	public void updateGroup(long id, GroupDTO upd);
	
	public List<GroupDTO> getAllGroups();
	
}
