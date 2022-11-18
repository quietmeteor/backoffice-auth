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
	private String creation_user;
	private String creation_time;
	private String update_user;
	private LocalDateTime update_time;
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

	public String getCreation_user() {
		return creation_user;
	}

	public void setCreation_user(String creation_user) {
		this.creation_user = creation_user;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public LocalDateTime getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(LocalDateTime update_time) {
		this.update_time = update_time;
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

	@Override
	public int hashCode() {
		return Objects.hash(creation_time, creation_user, enabled, groupName, id, permissions, roles, update_time,
				update_user);
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
		return Objects.equals(creation_time, other.creation_time) && Objects.equals(creation_user, other.creation_user)
				&& enabled == other.enabled && Objects.equals(groupName, other.groupName) && id == other.id
				&& Objects.equals(permissions, other.permissions) && Objects.equals(roles, other.roles)
				&& Objects.equals(update_time, other.update_time) && Objects.equals(update_user, other.update_user);
	}

	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", groupName=" + groupName + ", permissions=" + permissions + ", enabled="
				+ enabled + ", creation_user=" + creation_user + ", creation_time=" + creation_time + ", update_user="
				+ update_user + ", update_time=" + update_time + ", roles=" + roles + "]";
	}


}
