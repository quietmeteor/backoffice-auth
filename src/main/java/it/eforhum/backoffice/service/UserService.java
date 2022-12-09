package it.eforhum.backoffice.service;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;

public interface UserService {
	
	public void createUser(UserDTO user);

	public void deleteUser(UserDTO user);

	public void deleteUserCompletely(long id);

	public UserDTO findByUsername(String username);
	
	public UserDTO findById(long id);

	public void updateUser(UserDTO updatedUser);

	public void addUserToGroup(GroupDTO group, UserDTO user);

}
