package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.entity.UserGroups;

public interface RolesService {

	public List<String> getRolesList(UserGroups group);

	public void deleteRole(String role, UserGroups group);

	public void addRole(String role, UserGroups group);

}
