package it.eforhum.backoffice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import it.eforhum.backoffice.enums.Roles;

public class UserDTO {
	private long id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String lastName;
	private String groupName;
	private long groupId;
	private LocalDateTime lastLogin;
	private LocalDateTime dateModifiedPass;
	private LocalDateTime creationTime;
	private boolean verified;
	private boolean deleted;
	private String creationUser;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getDateModifiedPass() {
		return dateModifiedPass;
	}

	public void setDateModifiedPass(LocalDateTime dateModifiedPass) {
		this.dateModifiedPass = dateModifiedPass;
	}
	
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", lastName=" + lastName + ", groupName=" + groupName + ", groupId=" + groupId
				+ ", lastLogin=" + lastLogin + ", dateModifiedPass=" + dateModifiedPass + ", creationTime="
				+ creationTime + ", verified=" + verified + ", deleted=" + deleted + ", creationUser=" + creationUser
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationTime, creationUser, dateModifiedPass, deleted, email, groupId, groupName, id,
				lastLogin, lastName, name, password, username, verified);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(creationTime, other.creationTime) && Objects.equals(creationUser, other.creationUser)
				&& Objects.equals(dateModifiedPass, other.dateModifiedPass) && deleted == other.deleted
				&& Objects.equals(email, other.email) && groupId == other.groupId
				&& Objects.equals(groupName, other.groupName) && id == other.id
				&& Objects.equals(lastLogin, other.lastLogin) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username) && verified == other.verified;
	}

}
