package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.dto.GroupDTO;

public interface GroupService {
	
	public GroupDTO createGroup(GroupDTO group);
	
	public GroupDTO findGroupById(int id);

	public void deleteGroup(int id);	
	
	public void updateGroup(int id, GroupDTO upd);
	
	public List<GroupDTO> getAllGroups();
	
}
