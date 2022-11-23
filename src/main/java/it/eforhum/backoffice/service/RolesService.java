package it.eforhum.backoffice.service;

import java.util.List;

import it.eforhum.backoffice.dto.GroupDTO;

public interface RolesService {

	public List<String> getRolesList(GroupDTO group);

	public void deleteRole(String role, GroupDTO group);

	public void addRole(String role, GroupDTO group);

}
