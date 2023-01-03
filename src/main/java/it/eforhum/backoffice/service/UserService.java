package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;

public interface UserService {

	public void createUser(UserDTO user);

	public void deleteUser(UserDTO user);

	public void deleteUserCompletely(int id);

	public UserDTO findByUsername(String username);

	public UserDTO findById(int id);

	public void updateUser(UserDTO updatedUser, int id);

	public void addUserToGroup(GroupDTO group, UserDTO user);
	
	public List<UserDTO> getAllUsers();

}
