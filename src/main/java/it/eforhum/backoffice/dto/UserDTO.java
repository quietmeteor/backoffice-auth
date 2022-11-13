package it.eforhum.backoffice.dto;

import java.util.List;

import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;

public class UserDTO {
	long id;
	String username;
	String password;
	String email;
	String name;
	String lastName;
	UserGroups group;
	boolean verified;
	boolean deleted;
	List<Roles> listRoles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
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

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Roles> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<Roles> listRoles) {
		this.listRoles = listRoles;
	}

}
