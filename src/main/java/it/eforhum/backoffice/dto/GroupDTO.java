package it.eforhum.backoffice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import it.eforhum.backoffice.enums.Roles;

public class GroupDTO {
	private long id;
	private String groupName;
	private String permissions;
	private boolean enabled;
	private String creationUser;
	private LocalDateTime creationTime;
	private String updateUser;
	private LocalDateTime updateTime;
	private List<Roles> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationTime, creationUser, enabled, groupName, id, permissions, roles, updateTime,
				updateUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupDTO other = (GroupDTO) obj;
		return Objects.equals(creationTime, other.creationTime) && Objects.equals(creationUser, other.creationUser)
				&& enabled == other.enabled && Objects.equals(groupName, other.groupName) && id == other.id
				&& Objects.equals(permissions, other.permissions) && Objects.equals(roles, other.roles)
				&& Objects.equals(updateTime, other.updateTime) && Objects.equals(updateUser, other.updateUser);
	}

	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", groupName=" + groupName + ", permissions=" + permissions + ", enabled="
				+ enabled + ", creationUser=" + creationUser + ", creationTime=" + creationTime + ", updateUser="
				+ updateUser + ", updateTime=" + updateTime + ", roles=" + roles + "]";
	}

}
