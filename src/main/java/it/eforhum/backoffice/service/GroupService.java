package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.dto.GroupDTO;

public interface GroupService {
	
	public void createGroup(GroupDTO group);
	
<<<<<<< HEAD
	public void deleteGroup(long id);
	
	public GroupDTO findGroupById(long id);
	
	public void updateGroup(long id, GroupDTO upd);
=======
	public void deleteGroup(GroupDTO group);
	
	public GroupDTO findGroupById(long id);
	
	public void updateGroup(GroupDTO upd);
>>>>>>> master-web-userList
	
	public List<GroupDTO> getAllGroups();
	
}
