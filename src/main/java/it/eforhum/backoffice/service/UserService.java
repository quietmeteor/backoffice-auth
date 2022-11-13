package it.eforhum.backoffice.service;

import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.entity.UserGroups;

public interface UserService {
	
	public void createUser(UserDTO user);

	public void deleteUser(UserDTO user);

	public void deleteUserCompletely(UserDTO user);

	public UserDTO findByUsername(String username);
	
	public UserDTO findById(long id);

	public void updateUser(UserDTO updatedUser);

	public void addUserToGroup(UserGroups group, UserDTO user);

	public UserDTO login(String username, String password, String email);
	
}
