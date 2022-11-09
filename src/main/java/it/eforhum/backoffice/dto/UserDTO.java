package it.eforhum.backoffice.dto;

import java.util.List;

import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;

public class UserDTO {
	String username;
	String pasword;
	String email;
	String name;
	String lastName;
	UserGroups group;
	List<Roles> listRoles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserGroups getGroup() {
		return group;
	}

	public void setGroup(UserGroups group) {
		this.group = group;
	}

	public List<Roles> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<Roles> listRoles) {
		this.listRoles = listRoles;
	}
	

}
